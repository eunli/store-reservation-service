package com.example.store_reservation_service.service;

import com.example.store_reservation_service.dto.RegisterRequest;
import com.example.store_reservation_service.dto.TokenResponse;
import com.example.store_reservation_service.security.JwtProvider;
import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.dto.LoginRequest;
import com.example.store_reservation_service.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * 회원가입
     * - 이메일 중복 확인
     * - 비밀번호 암호화 후 저장
     * - 가입 후 이메일 전송
     */
    public void register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .name(registerRequest.getName())
                .phone(registerRequest.getPhone())
                .build();
        userRepository.save(user);
        sendWelcomeEmail(user.getEmail());
    }

    /**
     * 로그인
     * - 이메일, 비밀번호 확인 후 JWT 토큰 생성
     */
    public TokenResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtProvider.createToken(user.getEmail(), user.getRole());
                return new TokenResponse(token, jwtExpiration);
            }
        }
        throw new RuntimeException("이메일 또는 비밀번호가 일치하지 않습니다.");
    }

    /**
     * 회원가입 시 환영 이메일 발송
     */
    private void sendWelcomeEmail(String email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject("회원가입을 환영합니다!");
            helper.setText("회원가입이 완료되었습니다.");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송 실패: " + e.getMessage());
        }
    }
}
