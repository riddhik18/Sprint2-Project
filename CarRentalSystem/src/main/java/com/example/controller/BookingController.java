package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Booking;
import com.example.service.BookingService;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/bookings/new")
    public String createBookingForm(Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        return "create_booking";
    }

    @PostMapping("/bookings")
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        System.out.println("Received booking: " + booking);
        bookingService.createBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/edit/{id}")
    public String editBookingForm(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        return "edit_booking";
    }

    @PostMapping("/bookings/{id}")
    public String updateBooking(@PathVariable Long id,
            @ModelAttribute("booking") Booking booking,
            Model model) {

        Booking existingBooking = bookingService.getBookingById(id);
        existingBooking.setId(id);
        existingBooking.setCar(booking.getCar());
        existingBooking.setCustomerName(booking.getCustomerName());
        existingBooking.setRentalStartDate(booking.getRentalStartDate());
        existingBooking.setRentalEndDate(booking.getRentalEndDate());
        existingBooking.setPaymentStatus(booking.getPaymentStatus());

        bookingService.updateBooking(id, existingBooking);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
