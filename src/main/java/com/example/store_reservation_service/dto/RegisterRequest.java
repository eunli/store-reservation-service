package com.example.store_reservation_service.dto;

import com.example.store_reservation_service.domain.enums.Role;
import lombok.Data;

/**
 * 회원가입 요청 DTO
 * - 이메일, 비밀번호, 권한, 이름, 전화번호
 * - 권한 선택에 따라 매장 점장, 일반 사용자 가입
 */
@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
    private String name;
    private String phone;
}
