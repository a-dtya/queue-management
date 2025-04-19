package com.queue_management.shopservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItem { // this would be an embedded document within the services_offered field in mongodb
    private String name;
    private String description;
    private int durationInMinutes;
    private double price;
}
