package com.example.store_reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * JWT 토큰 응답 DTO
 */
@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Long expiration;
}
