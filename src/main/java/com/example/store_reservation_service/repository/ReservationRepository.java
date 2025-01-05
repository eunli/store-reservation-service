package com.example.store_reservation_service.repository;

import com.example.store_reservation_service.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 특정 사용자(user_id)의 예약 조회
    List<Reservation> findByUserId(Long userId);

    // 특정 매장(store_id)의 예약 조회
    List<Reservation> findByStoreId(Long storeId);
}