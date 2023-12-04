package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Booking;
import com.example.repository.BookingRepository;
import com.example.service.BookingService;

@Service
public class BookingServiceImplementation implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImplementation(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);

        if (existingBooking != null) {
            existingBooking.setCar(updatedBooking.getCar()); // Make sure getCar() returns a Car object
            existingBooking.setCustomerName(updatedBooking.getCustomerName());
            existingBooking.setRentalStartDate(updatedBooking.getRentalStartDate());
            existingBooking.setRentalEndDate(updatedBooking.getRentalEndDate());
            existingBooking.setPaymentStatus(updatedBooking.getPaymentStatus());

            bookingRepository.save(existingBooking);
        }
		return existingBooking;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
