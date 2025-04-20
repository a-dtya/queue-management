package com.queue_management.shopservice.repository;

import com.queue_management.shopservice.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
    List<Shop> findByOwnerId(String ownerId);
}
