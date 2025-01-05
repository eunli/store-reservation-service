package com.example.store_reservation_service.domain;

import com.example.store_reservation_service.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Reservation
 * 고객의 매장 예약 정보를 저장합니다.
 * - user: 예약한 고객
 * - store: 예약된 매장
 * - reservationTime: 예약 시간
 * - status: 예약 상태 (PENDING, CONFIRMED, CANCELLED)
 * - createdAt: 예약 생성 날짜
 * - updatedAt: 예약 정보 마지막 업데이트 날짜
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
