package com.MovieTicketBooking.MovieTicketBooking.User;



import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesRepo;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowDTO;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeRepo;
import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreModel;
import com.MovieTicketBooking.MovieTicketBooking.Theatre.TheatreRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenMovDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreTax.TheatreTaxModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreTax.TheatreTaxRepo;
import com.MovieTicketBooking.MovieTicketBooking.TicketBooking.*;
import com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge.TicketCateChargeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge.TicketcateChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    TheatreScreenRepo theatreScreenRepo;

    @Autowired
    TheatreRepo theatreRepo;

    @Autowired
    MovieDatesRepo movieDatesRepo;

    @Autowired
    ShowTimeRepo showTimeRepo;

    @Autowired
    TicketBookingRepo ticketBookingRepo;

    @Autowired
    TheatreTaxRepo theatreTaxRepo;

    @Autowired
    TicketcateChargeRepo ticketcateChargeRepo;

    //user reg
    public ResponseEntity<?> userreg(UserModel userModel) {

        if (isEmailAlreadyRegistered(userModel.getEmail())) {
            return new ResponseEntity<>("Email is already registered",HttpStatus.BAD_REQUEST);
        }
        UserModel userModel1 = new UserModel();
        userModel1.setName(userModel.getName());
        userModel1.setEmail(userModel.getEmail());
        userModel1.setPassword(userModel.getPassword());

        userRepo.save(userModel1);
        return new ResponseEntity<>(userModel1, HttpStatus.OK);
    }


    public boolean isEmailAlreadyRegistered(String email) {
        return userRepo.existsByEmail(email);
    }


    //user Login

    public ResponseEntity<?> userlogin(UserLoginDto userLoginDto) {
        if (userLoginDto.getEmail() == null || userLoginDto.getPassword() == null ||
                userLoginDto.getEmail().isEmpty() || userLoginDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Email or password is missing", HttpStatus.BAD_REQUEST);
        }

        Optional<UserModel> optionalUserModel = userRepo.findByEmailAndPassword(userLoginDto.getEmail(),
                userLoginDto.getPassword());

        if (optionalUserModel.isPresent()) {
            UserModel user = optionalUserModel.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }



    //Search movie by user
    public ResponseEntity<List<MovieModel>> searchmovie(String movieName) {
        List<MovieModel> movieModels = movieRepo.findByMovieNameContainingIgnoreCase(movieName);
        return new ResponseEntity<>(movieModels,HttpStatus.OK);
    }

    //get all movies
    public ResponseEntity<List<MovieModel>> getallmovies() {
        List<MovieModel> movieModels = movieRepo.findAll();
        return new ResponseEntity<>(movieModels,HttpStatus.OK);
    }

    public ResponseEntity<?> resetuserpassword(UserLoginDto userLoginDto) {
        Optional<UserModel> optionalUserModel = userRepo.findByEmail(userLoginDto.getEmail());
        if (optionalUserModel.isPresent()){
            UserModel userModel = optionalUserModel.get();
            userModel.setPassword(userLoginDto.getPassword());
            userRepo.save(userModel);
            return new ResponseEntity<>(userModel,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }


    }

    @Transactional
    public List<TheatreScreenMovDTO> getTheatreScreensByMovieId(Integer movieId) {
        List<TheatreScreenModel> screens = theatreScreenRepo.findByMovie_MovieId(movieId);
        List<TheatreScreenMovDTO> responseList = new ArrayList<>();

        for (TheatreScreenModel screen : screens) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();

            Integer theatreId = screen.getTheatreId();
            Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theatreId);

            if (optionalTheatreModel.isPresent()) {
                TheatreModel theatre = optionalTheatreModel.get();
                dto.setTheatreId(theatreId);
                dto.setName(theatre.getName());
                dto.setLatitude(theatre.getLatitude());   // ← Add latitude
                dto.setLongitude(theatre.getLongitude()); // ← Add longitude
            } else {
                dto.setTheatreId(theatreId);
                dto.setName("Unknown Theatre");
                dto.setLatitude(null);
                dto.setLongitude(null);
            }

            dto.setScreenId(screen.getScreenId());
            dto.setScreenName(screen.getScreenName());
            dto.setSeatCapacity(screen.getSeatCapacity());

            MovieModel movie = screen.getMovie();
            if (movie != null) {
                dto.setMovieId(movie.getMovieId());
                dto.setMovieName(movie.getMovieName());
            }

            movieDatesRepo.findByScreenId(screen.getScreenId()).ifPresent(movieDate -> {
                dto.setDateId(movieDate.getDateId());
                dto.setMovStart(movieDate.getMovStart());
                dto.setMovEnd(movieDate.getMovEnd());
            });

            List<ShowTimeModel> showTimes = showTimeRepo.findAllByMovieIdAndTheatreId(movieId, theatreId);
            List<ShowDTO> showTimeDTOs = showTimes.stream().map(showTime -> {
                ShowDTO stDto = new ShowDTO();
                stDto.setShowTimeId(showTime.getShowtimeId());
                stDto.setShowStart(showTime.getShowStart());
                return stDto;
            }).collect(Collectors.toList());

            dto.setShowTimes(showTimeDTOs);

            long availableSeats = screen.getAvailableSeats() != null
                    ? screen.getAvailableSeats().intValue()
                    : screen.getSeatCapacity().intValue();

            dto.setAvailableSeats((int) availableSeats);

            responseList.add(dto);
        }

        return responseList;
    }


    public ResponseEntity<List<MovieModel>> getmovie(Integer movieId) {
        Optional<MovieModel> movieOpt = movieRepo.findById(movieId);
        if (movieOpt.isPresent()) {
            return ResponseEntity.ok(Collections.singletonList(movieOpt.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional  // Make sure this method runs in a transaction
    public TicketBookingModel bookTicket(TicketBookingModel booking) {
        long totalBeforeTax = 0L;
        int totalTicketsBooked = 0;

        // Step 1: Calculate price for each category and accumulate totals
        for (TicketCategoryBookingModel categoryBooking : booking.getCategoryBookings()) {
            TicketCateChargeModel charge = ticketcateChargeRepo
                    .findByTheatreIdAndTicketcate(booking.getTheatreId(), categoryBooking.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Ticket category not found for theatre"));

            Long price = charge.getTicketcharge();
            categoryBooking.setPricePerTicket(price);
            categoryBooking.setTotalPrice(price * categoryBooking.getQuantity());
            categoryBooking.setBooking(booking); // For cascade save

            totalBeforeTax += categoryBooking.getTotalPrice();
            totalTicketsBooked += categoryBooking.getQuantity();
        }

        // Step 2: Tax calculation
        TheatreTaxModel taxModel = theatreTaxRepo.findByTheatreId(booking.getTheatreId()).orElse(null);
        double taxPercentage = taxModel != null ? taxModel.getTaxPercentage() : 0.0;
        double taxAmount = totalBeforeTax * (taxPercentage / 100);
        double totalWithTax = totalBeforeTax + taxAmount;

        booking.setTotalBeforeTax(totalBeforeTax);
        booking.setTaxPercentage(taxPercentage);
        booking.setTaxAmount(taxAmount);
        booking.setTotalWithTax(totalWithTax);

        // Step 3: Validate screen capacity per show date and time
        TheatreScreenModel screen = theatreScreenRepo.findById(booking.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        // Step 4: Fetch existing bookings with pessimistic lock to avoid race condition
        List<TicketBookingModel> existingBookings = ticketBookingRepo
                .findByScreenIdAndShowDateAndShowTimeForUpdate(
                        booking.getScreenId(), booking.getShowDate(), booking.getShowTime());

        // Step 5: Sum all tickets already booked at this screen, date, and time
        int alreadyBookedTickets = 0;
        for (TicketBookingModel b : existingBookings) {
            for (TicketCategoryBookingModel cb : b.getCategoryBookings()) {
                alreadyBookedTickets += cb.getQuantity();
            }
        }

        // Calculate available seats dynamically
        Long totalCapacity = screen.getSeatCapacity();
        Long availableSeatsForShow = totalCapacity - alreadyBookedTickets;

        if (availableSeatsForShow < totalTicketsBooked) {
            throw new RuntimeException("Not enough seats available for the selected show date and time");
        }

        booking.setSeatCapacityBeforeBooking(availableSeatsForShow);
        booking.setSeatCapacityAfterBooking(availableSeatsForShow - totalTicketsBooked);

        booking.setBookingDateTime(LocalDateTime.now());

        // Step 6: Save booking (cascade saves category bookings)
        return ticketBookingRepo.save(booking);
    }



    public ResponseEntity<String> getUser(Integer userId) {
        Optional<UserModel> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            String userJson = String.format("{\"name\": \"%s\", \"email\": \"%s\"}",
                    user.getName(), user.getEmail());
            return ResponseEntity.ok(userJson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    public AvailableSeatsDTO getAvailableSeats(Integer screenId, LocalDate showDate, LocalTime showTime) {
        Optional<TheatreScreenModel> screenOpt = theatreScreenRepo.findById(screenId);
        if (!screenOpt.isPresent()) {
            throw new RuntimeException("Screen not found with ID: " + screenId);
        }

        TheatreScreenModel screen = screenOpt.get();
        Long totalSeats = screen.getSeatCapacity();

        // Get all bookings for the screen at the specified date and time
        List<TicketBookingModel> bookings = ticketBookingRepo
                .findByScreenIdAndShowDateAndShowTime(screenId, showDate, showTime);

        // Sum up all booked seats from category bookings
        int bookedSeats = bookings.stream()
                .flatMap(b -> b.getCategoryBookings().stream())
                .mapToInt(TicketCategoryBookingModel::getQuantity)
                .sum();

        Long availableSeats = totalSeats - bookedSeats;

        return new AvailableSeatsDTO(
                screen.getScreenId(),
                screen.getScreenName(),
                showDate,
                showTime,
                availableSeats
        );
    }

    public List<TicketBookingModel> getAllBookings() {
        return ticketBookingRepo.findAll();

    }

//    public void createBooking(BookingRequestDTO bookingRequestDTO) {
//        System.out.println("User ID: " + bookingRequestDTO.getUserId());
//        System.out.println("Movie: " + bookingRequestDTO.getMovieName());
//        System.out.println("Theatre: " + bookingRequestDTO.getTheatreName());
//        System.out.println("Show Date & Time: " + bookingRequestDTO.getShowDate() + " " + bookingRequestDTO.getShowTime());
//
//        long totalTickets = bookingRequestDTO.getCategoryBookings().stream()
//                .mapToLong(CategoryBooking::getQuantity)
//                .sum();
//
//        // Step 1: Fetch and validate screen capacity
//        TheatreScreenModel screen = theatreScreenRepo.findById(bookingRequestDTO.getScreenId())
//                .orElseThrow(() -> new RuntimeException("Screen not found"));
//
//        Long capacityBefore = screen.getSeatCapacity();
//        if (capacityBefore < totalTickets) {
//            throw new RuntimeException("Not enough seats available on screen");
//        }
//
//        Long capacityAfter = capacityBefore - totalTickets;
//
//        // Optional: Save before/after in your model or print/log
//        System.out.println("Seat capacity before booking: " + capacityBefore);
//        System.out.println("Seat capacity after booking: " + capacityAfter);
//
//        // Step 2: Update screen capacity
//        screen.setSeatCapacity(capacityAfter);
//        theatreScreenRepo.save(screen);
//
//        // Step 3: Save booking (Map DTO to entity)
//        TicketBookingModel bookingEntity = new TicketBookingModel();
//        bookingEntity.setUserId(bookingRequestDTO.getUserId());
//        bookingEntity.setMovieId(bookingRequestDTO.getMovieId());
//        bookingEntity.setMovieName(bookingRequestDTO.getMovieName());
//        bookingEntity.setTheatreId(bookingRequestDTO.getTheatreId());
//        bookingEntity.setTheatreName(bookingRequestDTO.getTheatreName());
//        bookingEntity.setScreenId(bookingRequestDTO.getScreenId());
//        bookingEntity.setScreenName(bookingRequestDTO.getScreenName());
//        bookingEntity.setShowDate(bookingRequestDTO.getShowDate());
//        bookingEntity.setShowTime(bookingRequestDTO.getShowTime());
//        bookingEntity.setSeatCapacityBeforeBooking(capacityBefore);
//        bookingEntity.setSeatCapacityAfterBooking(capacityAfter);
//        bookingEntity.setCategoryBookings(bookingRequestDTO.getCategoryBookings()); // map properly if needed
//
//        ticketBookingRepo.save(bookingEntity);
//    }

}
