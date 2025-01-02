package com.example.store_reservation_service.repository;

import com.example.store_reservation_service.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByPartnerId(Long partnerId);
    List<Store> findByNameContaining(String name);
}