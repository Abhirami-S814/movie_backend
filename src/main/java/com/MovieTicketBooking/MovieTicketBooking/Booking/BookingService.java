package com.MovieTicketBooking.MovieTicketBooking.Booking;

import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeRepo;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryRepo;
import com.MovieTicketBooking.MovieTicketBooking.User.UserModel;
import com.MovieTicketBooking.MovieTicketBooking.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired BookingRepo bookingRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ShowTimeRepo showTimeRepo;
    @Autowired
    TicketCategoryRepo ticketCategoryRepo;

//    public BookingModel bookTicket(BookingRequest request) {
//        UserModel user = userRepo.findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        ShowTimeModel showTime = showTimeRepo.findById(request.getShowTimeId())
//                .orElseThrow(() -> new RuntimeException("ShowTime not found"));
//
//        TicketCategoryModel category = ticketCategoryRepo.findById(request.getTicketCateId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        int availableSeats = checkSeatAvailability(request.getShowTimeId(), request.getTicketCateId());
//        if (availableSeats < request.getNumberOfSeats()) {
//            throw new RuntimeException("Not enough seats available");
//        }
//
//        double totalAmount = calculateTotalAmount(showTime.getShowtimeId(), category.getTicketCateId(), request.getNumberOfSeats());
//
//        BookingModel booking = new BookingModel();
//        booking.setUserModel(user);
//        booking.setShowTimeModel(showTime);
//        booking.setTicketCategory(category);
//        booking.setNumberOfSeats(request.getNumberOfSeats());
//        booking.setTotalAmount(totalAmount);
//        booking.setBookingTime(LocalDateTime.now());
//
//        return bookingRepo.save(booking);
//    }

//    @Override
//    public int checkSeatAvailability(Long showTimeId, Integer ticketCateId) {
//        Integer booked = bookingRepo.countSeatsByShowTimeIdAndCategory(showTimeId, ticketCateId);
//        int maxSeats = 100; // Adjust based on screen or category logic
//        return maxSeats - (booked != null ? booked : 0);
//    }
//
//    @Override
//    public double calculateTotalAmount(Long showTimeId, Integer ticketCateId, int seatCount) {
//        TicketCategoryModel category = ticketCategoryRepo.findById(ticketCateId)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        return seatCount * category.getPrice ();
//    }
}
