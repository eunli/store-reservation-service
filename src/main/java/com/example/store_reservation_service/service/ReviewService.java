package com.example.store_reservation_service.service;

import com.example.store_reservation_service.domain.Review;
import com.example.store_reservation_service.domain.Store;
import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.dto.ReviewRequest;
import com.example.store_reservation_service.repository.ReviewRepository;
import com.example.store_reservation_service.repository.StoreRepository;
import com.example.store_reservation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    // 리뷰 작성
    public void createReview(ReviewRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        reviewRepository.save(review);
    }

    // 리뷰 수정 (작성자만 수정 가능)
    public void updateReview(Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        if (!review.getUser().getId().equals(request.getUserId())) {
            throw new RuntimeException("리뷰 수정 권한이 없습니다.");
        }

        review.setComment(request.getComment());
        review.setRating(request.getRating());
        reviewRepository.save(review);
    }

    // 리뷰 삭제 (작성자 또는 매장 점장만 가능)
    public void deleteReview(Long reviewId, Long requestUserId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));

        Long storePartnerId = review.getStore().getPartner().getId();
        Long writerId = review.getUser().getId();

        if (!writerId.equals(requestUserId) && !storePartnerId.equals(requestUserId)) {
            throw new RuntimeException("리뷰 삭제 권한이 없습니다.");
        }

        reviewRepository.deleteById(reviewId);
    }
}
