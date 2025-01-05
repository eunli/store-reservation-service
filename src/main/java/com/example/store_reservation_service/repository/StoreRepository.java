package com.example.store_reservation_service.repository;

import com.example.store_reservation_service.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 점장(Partner)별 매장 목록 조회
    List<Store> findByPartnerId(Long partnerId);

    // 매장 이름 검색
    List<Store> findByNameContaining(String name);
}