package com.queue_management.queueservice.controller;

import com.queue_management.queueservice.dto.CreateQueueEntryRequest;
import com.queue_management.queueservice.dto.QueueEntryResponse;
import com.queue_management.queueservice.dto.UpdateQueueEntryRequest;
import com.queue_management.queueservice.model.QueueEntry;
import com.queue_management.queueservice.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @PostMapping
    public Mono<QueueEntryResponse> createQueueEntry(@RequestBody CreateQueueEntryRequest request) {
        return queueService.createQueueEntry(request);
    }

    @GetMapping("/shop/{shopId}")
    public Flux<QueueEntryResponse> getQueueByShop(@PathVariable String shopId) {
        return queueService.getQueueByShop(shopId);
    }

    @GetMapping("/user/{userId}")
    public Flux<QueueEntryResponse> getQueueByUser(@PathVariable String userId) {
        return queueService.getQueueByUser(userId);
    }

    @PutMapping("/{id}")
    public Mono<QueueEntryResponse> updateQueueEntry(@PathVariable String id, @RequestBody UpdateQueueEntryRequest request) {
        return queueService.updateQueueEntry(id, request);
    }
}

