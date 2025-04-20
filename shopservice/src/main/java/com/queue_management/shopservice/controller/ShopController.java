package com.queue_management.shopservice.controller;

import com.queue_management.shopservice.dto.CreateShopRequest;
import com.queue_management.shopservice.dto.ShopResponse;
import com.queue_management.shopservice.dto.UpdateShopRequest;
import com.queue_management.shopservice.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;
    @PostMapping
    public ResponseEntity<ShopResponse> createShop(@RequestBody CreateShopRequest request) {
        return ResponseEntity.ok(shopService.createShop(request));
    }
    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShopResponse> getShopById(@PathVariable String id) {
        return shopService.getShopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopResponse> updateShop(@PathVariable String id, @RequestBody UpdateShopRequest request) {
        return shopService.updateShop(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable String id) {
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }





}
