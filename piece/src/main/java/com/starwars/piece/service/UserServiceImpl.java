package com.starwars.piece.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.starwars.piece.model.Piece;
import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPiece;
import com.starwars.piece.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserPieceService userPieceService;
    private final PieceService pieceService;
    
    @Override
    public User findOrCreateUser(String deviceId, String userAgent, String ipAddress) {

        //user 조회 및 생성
        User user = userRepository.findByDeviceId(deviceId).orElseGet(() -> {
            User newUser = new User();
            newUser.setDeviceId(deviceId);
            newUser.setUserAgent(userAgent);
            newUser.setIpAddress(ipAddress);
            return userRepository.save(newUser);
        });

        return user;
    }

}
