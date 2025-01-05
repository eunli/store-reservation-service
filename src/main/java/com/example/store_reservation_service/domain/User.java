package com.example.store_reservation_service.domain;

import com.example.store_reservation_service.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * User
 * 서비스를 이용하는 고객 정보를 저장합니다.
 * - email: 고객의 이메일
 * - password: 고객의 비밀번호
 * - role: 고객 또는 매장 점장(USER or PARTNER)
 * - name: 고객의 이름
 * - phone: 고객의 전화번호
 * - createdAt: 계정 생성 날짜
 * - updatedAt: 계정 마지막 업데이트 날짜
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
