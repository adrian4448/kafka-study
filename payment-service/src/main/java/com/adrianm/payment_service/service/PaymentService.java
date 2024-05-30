package com.adrianm.payment_service.service;

import com.adrianm.payment_service.model.Payment;

public interface PaymentService {
    void sendPayment(Payment payment);
}
