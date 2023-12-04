package com.example.service;

import com.example.entity.Booking;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking saveBooking(Booking booking);

    void deleteBooking(Long id);

    Booking updateBooking(Long id, Booking updatedBooking);

	void createBooking(Booking booking);
}
