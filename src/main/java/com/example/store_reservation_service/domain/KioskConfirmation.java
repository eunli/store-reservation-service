package com.example.store_reservation_service.domain;

import com.example.store_reservation_service.domain.enums.ArrivalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * KioskConfirmation
 * 키오스크를 통해 해당 예약의 도착 여부를 기록합니다.
 * - reservation: 확인된 예약 정보
 * - confirmationTime: 확인된 시간
 * - arrivalStatus: 도착 확인 상태
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
    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus = ArrivalStatus.ARRIVED;

    @Column(nullable = false)
    private LocalDateTime confirmationTime;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
