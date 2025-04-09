package com.starwars.piece.service;

import java.util.Optional;

import com.starwars.piece.model.User;

public interface UserService {
    
    User findOrCreateUser(String deviceId, String userAgent, String ipAddress);
}
