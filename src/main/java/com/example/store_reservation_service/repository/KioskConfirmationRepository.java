package com.example.store_reservation_service.repository;

import com.example.store_reservation_service.domain.KioskConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KioskConfirmationRepository extends JpaRepository<KioskConfirmation, Long> {
    Optional<KioskConfirmation> findByReservationId(Long reservationId);
}