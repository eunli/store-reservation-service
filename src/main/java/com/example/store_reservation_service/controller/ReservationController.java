package com.example.store_reservation_service.controller;

import com.example.store_reservation_service.domain.Reservation;
import com.example.store_reservation_service.domain.enums.Status;
import com.example.store_reservation_service.dto.ReservationRequest;
import com.example.store_reservation_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * 예약 생성
     */
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    /**
     * 특정 사용자 예약 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getUserReservations(userId));
    }

    /**
     * 특정 매장 예약 조회 (점장 확인)
     */
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Reservation>> getStoreReservations(@PathVariable Long storeId) {
        return ResponseEntity.ok(reservationService.getStoreReservations(storeId));
    }

    /**
     * 예약 상태 변경 (PENDING -> CONFIRMED or CANCELLED)
     */
    @PutMapping("/{reservationId}/status")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long reservationId,
                                                          @RequestParam Status status) {
        reservationService.updateReservationStatus(reservationId, status);
        return ResponseEntity.ok("예약 상태가 변경되었습니다.");
    }
}
