package com.starwars.piece.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.starwars.piece.model.Piece;
import com.starwars.piece.repository.PieceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("pieceService")
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;
    
    @Override
    public Optional<Piece> findByRowNumber(Long rowNumber){
        return pieceRepository.findById(rowNumber);
    }

    @Override
    public Optional<Piece> findByName(String name){
        return pieceRepository.findByName(name);
    }
}
