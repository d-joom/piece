package com.starwars.piece.service;

import com.starwars.piece.model.Piece;
import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPiece;

public interface UserPieceService {
    boolean addUserPiece(User user, Piece piece);
}
