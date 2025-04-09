package com.starwars.piece.service;

import org.springframework.stereotype.Service;

import com.starwars.piece.model.Piece;
import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPiece;
import com.starwars.piece.repository.UserPieceRepository;
import com.starwars.piece.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userPieceService")
public class UserPieceServiceImpl implements UserPieceService {

    private final UserRepository userRepository;
    private final UserPieceService userPieceService;
    private final UserPieceRepository userPieceRepository;
    
    @Override
    public boolean addUserPiece(User user, Piece piece) {
        try {
            UserPiece userPiece = new UserPiece();
            userPiece.setUser(user);
            userPiece.setPiece(piece);
            userPieceRepository.save(userPiece);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
