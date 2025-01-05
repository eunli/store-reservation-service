package com.example.store_reservation_service.dto;

import lombok.Data;

/**
 * 매장 등록 요청 DTO
 * - 매장 이름, 위치, 설명
 */
@Data
public class StoreRequest {
    private String storeName;
    private String location;
    private String description;
}
