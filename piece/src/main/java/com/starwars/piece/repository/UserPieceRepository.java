package com.starwars.piece.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.piece.model.Piece;
import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPiece;

public interface UserPieceRepository extends JpaRepository<UserPiece, Long> {
    boolean existsByUserAndPiece(User user, Piece piece);
}
