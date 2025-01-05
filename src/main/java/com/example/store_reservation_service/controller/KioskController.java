package com.example.store_reservation_service.controller;

import com.example.store_reservation_service.dto.KioskRequest;
import com.example.store_reservation_service.service.KioskConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 키오스크 도착 확인 컨트롤러
 * - 예약 10분 전에 키오스크를 통해 도착 확인
 */
@RestController
@RequestMapping("/api/kiosk")
public class KioskController {

    @Autowired
    private KioskConfirmationService kioskService;

    /**
     * 도착 확인
     */
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmArrival(@RequestBody KioskRequest kioskRequest) {
        kioskService.confirmArrival(kioskRequest);
        return ResponseEntity.ok("도착 확인이 완료되었습니다.");
    }
}
