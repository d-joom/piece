package com.starwars.piece.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.piece.model.Prize;

public interface PrizeRepository extends JpaRepository<Prize, Long> {
    
}
