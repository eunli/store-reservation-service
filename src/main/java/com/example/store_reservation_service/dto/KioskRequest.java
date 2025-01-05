package com.example.store_reservation_service.dto;

import com.example.store_reservation_service.domain.enums.ArrivalStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 키오스크 도착 확인 요청 DTO
 * - reservationId: 확인할 예약 ID
 * - arrivalStatus: 도착 상태
 */
@Data
public class KioskRequest {
    private Long reservationId;
    private ArrivalStatus arrivalStatus;
    private LocalDateTime confirmationTime;
}
