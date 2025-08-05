package com.example.advancedtransaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class PaymentService {

    private final Random random = new Random();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String processPayment(String orderNumber, BigDecimal amount) {
        // Simulate payment processing with potential failure
        if (random.nextInt(10) < 2) { // 20% chance of failure
            throw new RuntimeException("Payment processing failed for order: " + orderNumber);
        }

        // Simulate payment processing time
        try {
            Thread.sleep(100 + random.nextInt(200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "PAY-" + orderNumber + "-" + System.currentTimeMillis();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void refundPayment(String paymentId) {
        // Simulate refund processing
        try {
            Thread.sleep(50 + random.nextInt(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}