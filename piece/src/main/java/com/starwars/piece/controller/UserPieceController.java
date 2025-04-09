package com.starwars.piece.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.UserMapper;
import com.starwars.piece.dto.UserResponseDto;
import com.starwars.piece.model.Piece;
import com.starwars.piece.model.User;
import com.starwars.piece.service.PieceService;
import com.starwars.piece.service.UserPieceService;
import com.starwars.piece.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/pieces")
@RequiredArgsConstructor
public class UserPieceController {

    private final UserService userService;
    private final PieceService pieceService;
    private final UserPieceService userPieceService;
    
    @GetMapping
    public ResponseEntity<UserResponseDto> accessPiece(
        @RequestParam String deviceId,
        @RequestParam String name,
        HttpServletRequest request
    ) {
        
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        
        User user = userService.findOrCreateUser(deviceId, userAgent, ipAddress);
        Piece piece = pieceService.findByName(name).orElse(null);

        //userPiece 생성
        if(piece == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean added = userPieceService.addUserPiece(user, piece);

        if (added) {
            UserResponseDto dto = UserMapper.toDto(user);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
