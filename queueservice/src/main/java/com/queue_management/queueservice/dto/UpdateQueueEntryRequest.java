package com.queue_management.queueservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateQueueEntryRequest {
    private LocalDateTime estimatedServiceTime;
    private LocalDateTime completedAt;
}
