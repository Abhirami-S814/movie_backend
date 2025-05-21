package com.MovieTicketBooking.MovieTicketBooking.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/bookingdetails")
@CrossOrigin
public class BookingController {

    @Autowired BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingModel> bookTicket(@RequestBody BookingRequest request) {
        BookingModel booking = bookingService.bookTicket(request);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/seats/availability")
    public ResponseEntity<Integer> checkAvailability(
            @RequestParam Long showTimeId,
            @RequestParam Integer ticketCateId
    ) {
        int availableSeats = bookingService.checkSeatAvailability(showTimeId, ticketCateId);
        return ResponseEntity.ok(availableSeats);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateCharge(
            @RequestParam Long showTimeId,
            @RequestParam Integer ticketCateId,
            @RequestParam int seatCount
    ) {
        double charge = bookingService.calculateTotalAmount(showTimeId, ticketCateId, seatCount);
        return ResponseEntity.ok(charge);
    }

}
