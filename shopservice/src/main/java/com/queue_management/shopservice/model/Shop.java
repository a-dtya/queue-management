package com.queue_management.shopservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "shops")
@Builder // for mongo, creates objects easily
@AllArgsConstructor
@NoArgsConstructor // when you want to generate constructor with no arguments
public class Shop {
    @Id
    private String id;
    private String name;
    private String address;
    @Field("contact_email")
    private String contactEmail;
    @Field("contact_phone")
    private String contactPhone;
    @Field("owner_id")
    private String ownerId; // link to user (from UserService)

    @Field("services_offered")
    private List<ServiceItem> servicesOffered; // e.g. ["Haircut", "Consultation"]

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();
}
