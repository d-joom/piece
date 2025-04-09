package com.starwars.piece.dto;

import java.util.List;

import com.starwars.piece.model.Piece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String deviceId;
    private String userAgent;
    private String ipAddress;
    private List<String> pieceNames; // 유저가 가진 조각들 이름
}