package com.example.store_reservation_service.dto;

import lombok.Data;

/**
 * 로그인 요청 DTO
 * - 이메일, 비밀번호
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
