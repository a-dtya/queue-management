package com.queue_management.queueservice.repository;

import com.queue_management.queueservice.model.QueueEntry;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface QueueRepository extends ReactiveMongoRepository<QueueEntry, String> {

    // Returns a Flux of QueueEntry sorted by position in ascending order for a specific shopId. (Query derivation is a way of querying from method name)
    Flux<QueueEntry> findByShopIdOrderByPositionAsc(String shopId);

    // Returns a Flux of QueueEntry for a specific userId
    Flux<QueueEntry> findByUserId(String userId);
}
