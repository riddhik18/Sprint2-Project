package com.example.service.implementation;

import com.example.entity.Payment;
import com.example.repository.PaymentRepository;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImplementation(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(id);

        if (existingPayment != null) {
            // Update fields as needed
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setPaymentMode(updatedPayment.getPaymentMode());
            existingPayment.setPaymentMode(updatedPayment.getPaymentMode());

            paymentRepository.save(existingPayment);
        }
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
