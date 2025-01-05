package com.example.store_reservation_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 예약 요청 DTO
 * - userId: 예약을 진행하는 사용자
 * - storeId: 예약할 매장
 * - reservationTime: 예약 시간
 */
@Data
public class ReservationRequest {
    private Long userId;
    private Long storeId;
    private LocalDateTime reservationTime;
}
