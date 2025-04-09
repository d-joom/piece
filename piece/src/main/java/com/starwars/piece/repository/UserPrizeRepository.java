package com.starwars.piece.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPrize;

public interface UserPrizeRepository extends JpaRepository<UserPrize, Long> {
    boolean existsByUser(User user);
}
