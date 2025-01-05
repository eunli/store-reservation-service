package com.example.store_reservation_service.service;

import com.example.store_reservation_service.domain.Store;
import com.example.store_reservation_service.domain.User;
import com.example.store_reservation_service.dto.StoreRequest;
import com.example.store_reservation_service.repository.StoreRepository;
import com.example.store_reservation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 매장 등록
     */
    public void registerStore(Long partnerId, StoreRequest storeRequest) {
        User partner = userRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("파트너(사용자) 정보를 찾을 수 없습니다."));

        Store store = Store.builder()
                .partner(partner)
                .name(storeRequest.getStoreName())
                .location(storeRequest.getLocation())
                .description(storeRequest.getDescription())
                .build();
        storeRepository.save(store);
    }

    /**
     * 매장 수정
     */
    public void updateStore(Long storeId, Store updatedStore, Long partnerId) {
        Store existingStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));

        if (!existingStore.getPartner().getId().equals(partnerId)) {
            throw new RuntimeException("해당 매장을 수정할 권한이 없습니다.");
        }

        existingStore.setName(updatedStore.getName());
        existingStore.setLocation(updatedStore.getLocation());
        existingStore.setDescription(updatedStore.getDescription());
        storeRepository.save(existingStore);
    }

    /**
     * 매장 삭제
     */
    public void deleteStore(Long storeId, Long partnerId) {
        Store existingStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));

        if (!existingStore.getPartner().getId().equals(partnerId)) {
            throw new RuntimeException("해당 매장을 삭제할 권한이 없습니다.");
        }

        storeRepository.deleteById(storeId);
    }

    /**
     * 모든 매장 조회
     */
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    /**
     * 특정 점장이 등록한 매장 조회
     */
    public List<Store> getStoresByPartner(Long partnerId) {
        return storeRepository.findByPartnerId(partnerId);
    }

    /**
     * 매장 이름 검색
     */
    public List<Store> searchStoreByName(String name) {
        return storeRepository.findByNameContaining(name);
    }
}
