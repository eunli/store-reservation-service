package com.example.store_reservation_service.service;

import com.example.store_reservation_service.domain.KioskConfirmation;
import com.example.store_reservation_service.domain.Reservation;
import com.example.store_reservation_service.domain.enums.ArrivalStatus;
import com.example.store_reservation_service.dto.KioskRequest;
import com.example.store_reservation_service.repository.KioskConfirmationRepository;
import com.example.store_reservation_service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KioskConfirmationService {

    @Autowired
    private KioskConfirmationRepository kioskConfirmationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * 키오스크 도착 확인 생성
     */
    public void confirmArrival(KioskRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("해당 예약을 찾을 수 없습니다."));

        // 현재 도착 시간
        LocalDateTime confirmTime = request.getConfirmationTime() != null
                ? request.getConfirmationTime()
                : LocalDateTime.now();

        // 예약시간에서 10분 뺀 시각
        LocalDateTime latestAllowedTime = reservation.getReservationTime().minusMinutes(10);

        KioskConfirmation kiosk = kioskConfirmationRepository.findByReservationId(request.getReservationId())
                .orElse(KioskConfirmation.builder()
                        .reservation(reservation)
                        .createdAt(LocalDateTime.now())
                        .build());

        kiosk.setConfirmationTime(confirmTime);

        // 도착 시간 검증 (10분 전보다 늦게 왔다면 노쇼)
        if (confirmTime.isAfter(latestAllowedTime)) {
            kiosk.setArrivalStatus(ArrivalStatus.NO_SHOW);
        } else {
            kiosk.setArrivalStatus(ArrivalStatus.ARRIVED);
        }

        kioskConfirmationRepository.save(kiosk);
    }
}
