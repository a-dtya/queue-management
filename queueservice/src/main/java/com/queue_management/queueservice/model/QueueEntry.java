package com.queue_management.queueservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "queue_entries")
public class QueueEntry {

    @Id
    private String id;

    private String userId;      // ID of the user joining the queue
    private String shopId;      // ID of the shop the user is queuing at
    private String serviceName; // Name of the service (e.g., "Haircut")

    private int position;       // Position in the queue

    @CreatedDate
    private LocalDateTime joinedAt;   // Time when user joined the queue
    private LocalDateTime estimatedServiceTime; // Optional: estimated service time
    private LocalDateTime completedAt;          // If the service is completed
}
