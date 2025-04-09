package com.starwars.piece.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.piece.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDeviceId(String deviceId);

}
