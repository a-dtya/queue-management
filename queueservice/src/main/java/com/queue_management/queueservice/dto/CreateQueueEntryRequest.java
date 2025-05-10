package com.queue_management.queueservice.dto;

import lombok.Data;

@Data
public class CreateQueueEntryRequest {
    private String userId;
    private String shopId;
    private String serviceName;
}
