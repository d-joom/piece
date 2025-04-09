package com.starwars.piece.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_number")
    private Long rowNumber;

    @Column(unique = true, name = "device_id")
    private String deviceId;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "ip_address")
    private String ipAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "user_pieces")
    private List<UserPiece> userPieces = new ArrayList<>();

    @Column(name = "user_prize")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPrize userPrize = new UserPrize();

    @Column(name= " created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name= " last_seen")
    private LocalDateTime lastSeen;
}
