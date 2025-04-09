package com.starwars.piece.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starwars.piece.model.User;
import com.starwars.piece.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("/add")
    public ResponseEntity<User> addUser(
        @RequestParam String deviceId,
        HttpServletRequest request
    ) {
    String userAgent = request.getHeader("User-Agent");
    
    String ipAddress = null;

    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
        ipAddress = request.getRemoteAddr();
    } else {
        ipAddress =  xfHeader.split(",")[0];
    }

    User user = userService.findOrCreateUser(deviceId, userAgent, ipAddress);
    return ResponseEntity.ok(user);
    }
}
