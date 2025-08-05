package com.example.advancedtransaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendOrderConfirmation(String orderNumber, String customerEmail) {
        // Simulate sending email notification
        System.out.println("Sending order confirmation email to " + customerEmail + " for order " + orderNumber);

        // Simulate potential failure in notification service
        if (Math.random() < 0.1) { // 10% chance of failure
            throw new RuntimeException("Failed to send notification for order: " + orderNumber);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendOrderCancellation(String orderNumber, String customerEmail) {
        System.out.println("Sending order cancellation email to " + customerEmail + " for order " + orderNumber);
    }
}