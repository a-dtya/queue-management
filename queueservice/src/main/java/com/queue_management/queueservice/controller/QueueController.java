package com.queue_management.queueservice.controller;

import com.queue_management.queueservice.model.QueueEntry;
import com.queue_management.queueservice.service.QueueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/queues")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    // Get all queue entries for a specific shop, ordered by position
    @GetMapping("/shop/{shopId}")
    public Flux<QueueEntry> getQueueEntriesByShopId(@PathVariable String shopId) {
        return queueService.getQueueEntriesByShopId(shopId);
    }

    // Get all queue entries for a specific user
    @GetMapping("/user/{userId}")
    public Flux<QueueEntry> getQueueEntriesByUserId(@PathVariable String userId) {
        return queueService.getQueueEntriesByUserId(userId);
    }

    // Create a new queue entry
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<QueueEntry> createQueueEntry(@RequestBody QueueEntry queueEntry) {
        return queueService.createQueueEntry(queueEntry);
    }

    // Update a queue entry
    @PutMapping("/{id}")
    public Mono<QueueEntry> updateQueueEntry(@PathVariable String id, @RequestBody QueueEntry queueEntry) {
        return queueService.updateQueueEntry(id, queueEntry);
    }

    // Delete a queue entry
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteQueueEntry(@PathVariable String id) {
        return queueService.deleteQueueEntry(id);
    }
}
