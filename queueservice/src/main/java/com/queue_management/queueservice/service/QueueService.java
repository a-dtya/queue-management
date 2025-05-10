package com.queue_management.queueservice.service;

import com.queue_management.queueservice.dto.CreateQueueEntryRequest;
import com.queue_management.queueservice.dto.QueueEntryResponse;
import com.queue_management.queueservice.dto.UpdateQueueEntryRequest;
import com.queue_management.queueservice.model.QueueEntry;
import com.queue_management.queueservice.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;

    public Flux<QueueEntryResponse> getQueueByShop(String shopId) {
        return queueRepository.findByShopIdOrderByPositionAsc(shopId)
                .map(this::mapToResponse);
    }

    public Flux<QueueEntryResponse> getQueueByUser(String userId) {
        return queueRepository.findByUserId(userId)
                .map(this::mapToResponse);
    }
    public Mono<QueueEntryResponse> createQueueEntry(CreateQueueEntryRequest request) {
        return queueRepository.findByShopIdOrderByPositionAsc(request.getShopId())
                .count()
                .map(count -> QueueEntry.builder()
                        .userId(request.getUserId())
                        .shopId(request.getShopId())
                        .serviceName(request.getServiceName())
                        .position(Math.toIntExact(count) + 1)
                        .joinedAt(LocalDateTime.now())
                        .build()
                )
                .flatMap(queueRepository::save)
                .map(this::mapToResponse);
    }

    public Mono<QueueEntryResponse> updateQueueEntry(String id, UpdateQueueEntryRequest request) {
        return queueRepository.findById(id)
                .map(entry -> {
                    entry.setCompletedAt(request.getCompletedAt());
                    return entry;
                })
                .flatMap(queueRepository::save)
                .map(this::mapToResponse);
    }
    private QueueEntryResponse mapToResponse(QueueEntry entry) {
        QueueEntryResponse response = new QueueEntryResponse();
        response.setId(entry.getId());
        response.setUserId(entry.getUserId());
        response.setShopId(entry.getShopId());
        response.setServiceName(entry.getServiceName());
        response.setPosition(entry.getPosition());
        response.setJoinedAt(entry.getJoinedAt());
        response.setEstimatedServiceTime(entry.getEstimatedServiceTime());
        response.setCompletedAt(entry.getCompletedAt());
        return response;
    }
}
