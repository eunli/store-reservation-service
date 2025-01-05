package com.example.store_reservation_service.service;

import com.example.store_reservation_service.domain.Reservation;
import com.example.store_reservation_service.domain.Store;
import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.domain.enums.Status;
import com.example.store_reservation_service.dto.ReservationRequest;
import com.example.store_reservation_service.repository.ReservationRepository;
import com.example.store_reservation_service.repository.StoreRepository;
import com.example.store_reservation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    /**
     * 예약 생성
     */
    public void createReservation(ReservationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));

        Reservation reservation = Reservation.builder()
                .user(user)
                .store(store)
                .reservationTime(request.getReservationTime())
                .status(Status.PENDING)
                .build();

        reservationRepository.save(reservation);
    }

    /**
     * 특정 사용자 예약 조회
     */
    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    /**
     * 특정 매장 예약 조회
     */
    public List<Reservation> getStoreReservations(Long storeId) {
        return reservationRepository.findByStoreId(storeId);
    }

    /**
     * 예약 상태 변경 (확인, 취소 등)
     */
    public void updateReservationStatus(Long reservationId, Status newStatus) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("예약을 찾을 수 없습니다."));

        reservation.setStatus(newStatus);
        reservationRepository.save(reservation);
    }
}
