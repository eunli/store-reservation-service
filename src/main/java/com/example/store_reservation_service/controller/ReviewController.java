package com.example.store_reservation_service.controller;

import com.example.store_reservation_service.domain.Review;
import com.example.store_reservation_service.dto.ReviewRequest;
import com.example.store_reservation_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 리뷰 작성
     */
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
        return ResponseEntity.ok("리뷰가 작성되었습니다.");
    }

    /**
     * 리뷰 수정
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody ReviewRequest reviewRequest) {
        reviewService.updateReview(reviewId, reviewRequest);
        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/{reviewId}/user/{userId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId,
                                               @PathVariable Long userId) {
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }
}
