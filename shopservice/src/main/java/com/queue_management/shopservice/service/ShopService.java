package com.queue_management.shopservice.service;

import com.queue_management.shopservice.dto.CreateShopRequest;
import com.queue_management.shopservice.dto.ShopResponse;
import com.queue_management.shopservice.dto.UpdateShopRequest;
import com.queue_management.shopservice.model.Shop;
import com.queue_management.shopservice.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopResponse createShop(CreateShopRequest request) {
        Shop shop = Shop.builder()
                .name(request.getName())
                .address(request.getAddress())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .ownerId(request.getOwnerId())
                .servicesOffered(request.getServicesOffered())
                .build();

        Shop savedShop = shopRepository.save(shop);
        return mapToResponse(savedShop);
    }

    public List<ShopResponse> getAllShops() {
        return shopRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<ShopResponse> getShopById(String id) {
        return shopRepository.findById(id)
                .map(this::mapToResponse);
    }

    public Optional<ShopResponse> updateShop(String id, UpdateShopRequest request) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(request.getName());
            shop.setAddress(request.getAddress());
            shop.setContactEmail(request.getContactEmail());
            shop.setContactPhone(request.getContactPhone());
            shop.setServicesOffered(request.getServicesOffered());
            shop.setUpdatedAt(java.time.LocalDateTime.now());
            return mapToResponse(shopRepository.save(shop));
        });
    }

    public void deleteShop(String id) {
        shopRepository.deleteById(id);
    }

    private ShopResponse mapToResponse(Shop shop) {
        ShopResponse response = new ShopResponse();
        response.setId(shop.getId());
        response.setName(shop.getName());
        response.setAddress(shop.getAddress());
        response.setContactEmail(shop.getContactEmail());
        response.setContactPhone(shop.getContactPhone());
        response.setOwnerId(shop.getOwnerId());
        response.setServicesOffered(shop.getServicesOffered());
        response.setCreatedAt(shop.getCreatedAt());
        response.setUpdatedAt(shop.getUpdatedAt());
        return response;
    }
}
