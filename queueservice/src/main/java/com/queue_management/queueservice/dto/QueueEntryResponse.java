package com.queue_management.queueservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueueEntryResponse {
    private String id;
    private String userId;
    private String shopId;
    private String serviceName;
    private int position;
    private LocalDateTime joinedAt;
    private LocalDateTime estimatedServiceTime;
    private LocalDateTime completedAt;
}
