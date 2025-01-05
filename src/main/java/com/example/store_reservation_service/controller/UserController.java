package com.example.store_reservation_service.controller;

import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.dto.LoginRequest;
import com.example.store_reservation_service.dto.RegisterRequest;
import com.example.store_reservation_service.dto.TokenResponse;
import com.example.store_reservation_service.service.UserService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원가입
     * - 사용자 또는 매장 점장으로 가입
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok("회원가입이 완료되었습니다. 이메일을 확인하세요.");
    }

    /**
     * 로그인
     * - (로그아웃은 클라이언트 측에서 처리)
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = userService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
