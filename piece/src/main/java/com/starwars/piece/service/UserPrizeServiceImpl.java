package com.starwars.piece.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.piece.model.Piece;
import com.starwars.piece.model.Prize;
import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPiece;
import com.starwars.piece.model.UserPrize;
import com.starwars.piece.repository.PrizeRepository;
import com.starwars.piece.repository.UserPrizeRepository;
import com.starwars.piece.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userPrizeService")
public class UserPrizeServiceImpl implements UserPrizeService {

    private final PrizeRepository prizeRepository;
    private final UserPrizeRepository userPrizeRepository;
    private final UserPieceService userPieceService;

    
    @Override
    public UserPrize drawPrizeForUser(User user) {

        //응모했는지 확인
        if(userPrizeRepository.existsByUser(user)){
            throw new IllegalStateException("이미 응모한 사용자입니다.");
        }

        List<Prize> prizes = prizeRepository.findAll();

        //1~4등 경품
        List<Prize> tier1to4 = prizes.stream()
        .filter(p -> p.getRank() >= 1 && p.getRank() <= 4)
        .filter(p -> p.getRemaining() > 0)
        .collect(Collectors.toList());

        //랜덤으로 하나 선택
        Prize selected = drawByProbability(tier1to4);

        // 1~4등에서 못 뽑았으면 5등 남은 거 있는지 확인
        if (selected == null) {
            Prize tier5 = prizes.stream()
                .filter(p -> p.getRank() == 5)
                .filter(p -> p.getRemaining() > 0)
                .findFirst()
                .orElse(null);

            if (tier5 == null) {
                throw new IllegalStateException("모든 경품이 소진되었습니다.");
            }

            selected = tier5;
        }

        // 수량 감소
        selected.setRemaining(selected.getRemaining() - 1);
        prizeRepository.save(selected);

        //결과 저장
        UserPrize userPrize = new UserPrize();
        userPrize.setUser(user);
        userPrize.setPrize(selected);
        userPrize = userPrizeRepository.save(userPrize);

        return userPrize;
    }

    private Prize drawByProbability(List<Prize> prizes) {
        double totalWeight = prizes.stream().mapToDouble(Prize::getProbability).sum();
        double rand = Math.random() * totalWeight;
        double cumulative = 0.0;

        for (Prize prize : prizes) {
            cumulative += prize.getProbability();
            if (rand <= cumulative) {
                return prize;
            }
        }

        return null; // 당첨 안 된 경우
    }
}
