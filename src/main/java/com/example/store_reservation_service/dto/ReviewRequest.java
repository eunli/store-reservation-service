package com.example.store_reservation_service.dto;

import lombok.Data;

/**
 * 리뷰 작성/수정 요청 DTO
 * - userId, storeId, rating, comment
 */
@Data
public class ReviewRequest {
    private Long userId;
    private Long storeId;
    private int rating;
    private String comment;
}
