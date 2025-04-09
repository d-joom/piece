package com.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.starwars.piece.dto.UserResponseDto;
import com.starwars.piece.model.User;

public class UserMapper {

    public static UserResponseDto toDto(User user) {
        List<String> pieceNames = Optional.ofNullable(user.getUserPieces())
        .orElse(Collections.emptyList())
        .stream()
        .map(userPiece -> userPiece.getPiece().getName())
        .collect(Collectors.toList());

        return new UserResponseDto(
                user.getRowNumber(),
                user.getDeviceId(),
                user.getUserAgent(),
                user.getIpAddress(),
                pieceNames
        );
    }
}
