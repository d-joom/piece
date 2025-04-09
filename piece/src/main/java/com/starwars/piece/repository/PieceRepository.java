package com.starwars.piece.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.piece.model.Piece;

public interface PieceRepository extends JpaRepository<Piece, Long> {
    Optional<Piece> findByName(String name);
}
