package com.starwars.piece.service;

import java.util.Optional;

import com.starwars.piece.model.Piece;

public interface PieceService {
    Optional<Piece> findByRowNumber(Long rowNumber);
    Optional<Piece> findByName(String name);
}
