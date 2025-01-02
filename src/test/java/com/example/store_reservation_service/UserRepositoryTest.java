package com.example.store_reservation_service;

import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void insertUserTest() {
        //given
        User newUser = User.builder()
                .name("John Doe")
                .email("johndoe@example.com")
                .password("securepassword")
                .phone("123-456-7890")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        //when
        User savedUser = userRepository.save(newUser);

        //then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("johndoe@example.com");
        assertThat(savedUser.getPassword()).isEqualTo("securepassword");
        assertThat(savedUser.getPhone()).isEqualTo("123-456-7890");
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getUpdatedAt()).isNotNull();
    }

}
