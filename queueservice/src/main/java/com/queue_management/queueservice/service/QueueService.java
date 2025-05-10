package com.queue_management.queueservice.service;

import com.queue_management.queueservice.model.QueueEntry;
import com.queue_management.queueservice.repository.QueueRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class QueueService {

    private final QueueRepository queueRepository;

    // Constructor Injection of the QueueRepository
    public QueueService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    // Method to get all queue entries for a shop, ordered by position
    public Flux<QueueEntry> getQueueEntriesByShopId(String shopId) {
        return queueRepository.findByShopIdOrderByPositionAsc(shopId);
    }

    // Method to get all queue entries for a specific user
    public Flux<QueueEntry> getQueueEntriesByUserId(String userId) {
        return queueRepository.findByUserId(userId);
    }

    // Method to create a new QueueEntry
    public Mono<QueueEntry> createQueueEntry(QueueEntry queueEntry) {
        return queueRepository.save(queueEntry);
    }

    // Method to update an existing QueueEntry
    public Mono<QueueEntry> updateQueueEntry(String id, QueueEntry queueEntry) {
        // Optional: Check if the QueueEntry exists before updating
        return queueRepository.findById(id)
                .flatMap(existingEntry -> {
                    existingEntry.setPosition(queueEntry.getPosition());
                    existingEntry.setServiceName(queueEntry.getServiceName());
                    existingEntry.setJoinedAt(queueEntry.getJoinedAt());
                    existingEntry.setEstimatedServiceTime(queueEntry.getEstimatedServiceTime());
                    existingEntry.setCompletedAt(queueEntry.getCompletedAt());
                    return queueRepository.save(existingEntry);
                });
    }

    // Method to delete a QueueEntry by its ID
    public Mono<Void> deleteQueueEntry(String id) {
        return queueRepository.deleteById(id);
    }
}
