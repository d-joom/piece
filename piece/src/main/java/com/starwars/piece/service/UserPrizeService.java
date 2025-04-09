package com.starwars.piece.service;

import java.util.Optional;

import com.starwars.piece.model.User;
import com.starwars.piece.model.UserPrize;

public interface UserPrizeService {
    
    UserPrize drawPrizeForUser(User user);
}
