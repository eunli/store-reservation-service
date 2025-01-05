package com.example.store_reservation_service.repository;

import com.example.store_reservation_service.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 매장별 리뷰 조회
    List<Review> findByStoreId(Long storeId);
    // 사용자별 리뷰 조회
    List<Review> findByUserId(Long userId);
}
