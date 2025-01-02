package com.example.store_reservation_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * KioskConfirmation
 * 키오스크를 통한 예약 확인 정보를 저장합니다.
 * - reservation: 확인된 예약 정보
 * - confirmationTime: 확인된 시간
 * - createdAt: 확인 정보 생성 날짜
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KioskConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private LocalDateTime confirmationTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
