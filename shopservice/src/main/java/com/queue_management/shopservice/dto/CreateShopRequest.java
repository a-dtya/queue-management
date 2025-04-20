package com.queue_management.shopservice.dto;

import com.queue_management.shopservice.model.ServiceItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateShopRequest {
    private String name;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private String ownerId;
    private List<ServiceItem> servicesOffered;
}
