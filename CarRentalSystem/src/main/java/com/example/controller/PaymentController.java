package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Payment;
import com.example.service.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments";
    }

    @GetMapping("/new")
    public String createPaymentForm(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "create_payment";
    }

    @PostMapping
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        System.out.println("Received payment: " + payment);
        paymentService.savePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        model.addAttribute("payment", paymentService.getPaymentById(id));
        return "edit_payment";
    }

    @PostMapping("/{id}")
    public String updatePayment(@PathVariable Long id,
                                @ModelAttribute("payment") Payment payment,
                                Model model) {

        Payment existingPayment = paymentService.getPaymentById(id);
        existingPayment.setId(id);
        existingPayment.setPaymentMode(payment.getPaymentMode());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentMode(payment.getPaymentMode());

        paymentService.updatePayment(id, existingPayment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}
