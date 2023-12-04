package com.example.service;

import com.example.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    void updatePayment(Long id, Payment updatedPayment);
    void deletePayment(Long id);
}
