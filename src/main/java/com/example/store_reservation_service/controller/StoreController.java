package com.example.store_reservation_service.controller;

import com.example.store_reservation_service.domain.Store;
import com.example.store_reservation_service.dto.StoreRequest;
import com.example.store_reservation_service.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * 매장 등록
     */
    @PostMapping("/register/{partnerId}")
    public ResponseEntity<String> registerStore(@PathVariable Long partnerId, @RequestBody StoreRequest storeRequest) {
        storeService.registerStore(partnerId, storeRequest);
        return ResponseEntity.ok("매장이 등록되었습니다.");
    }

    /**
     * 매장 수정
     */
    @PutMapping("/{storeId}/partner/{partnerId}")
    public ResponseEntity<String> updateStore(@PathVariable Long storeId,
                                              @PathVariable Long partnerId,
                                              @RequestBody Store store) {
        storeService.updateStore(storeId, store, partnerId);
        return ResponseEntity.ok("매장이 수정되었습니다.");
    }

    /**
     * 매장 삭제
     */
    @DeleteMapping("/{storeId}/partner/{partnerId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId,
                                              @PathVariable Long partnerId) {
        storeService.deleteStore(storeId, partnerId);
        return ResponseEntity.ok("매장이 삭제되었습니다.");
    }

    /**
     * 모든 매장 조회
     */
    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    /**
     * 점장(파트너)별 매장 조회
     */
    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<List<Store>> getStoresByPartner(@PathVariable Long partnerId) {
        return ResponseEntity.ok(storeService.getStoresByPartner(partnerId));
    }

    /**
     * 매장 이름 검색
     */
    @GetMapping("/search")
    public ResponseEntity<List<Store>> searchStores(@RequestParam("name") String name) {
        return ResponseEntity.ok(storeService.searchStoreByName(name));
    }
}
