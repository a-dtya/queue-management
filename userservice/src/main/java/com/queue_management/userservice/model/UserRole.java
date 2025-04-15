package com.queue_management.userservice.model;

public enum UserRole {
    USER,           // Customers booking the queue
    ADMIN,          // System admins who manage the platform
    SHOP_OWNER,     // Shop/clinic/salon owners who manage their own queues
    STAFF           // Receptionists or workers at the shop
}
