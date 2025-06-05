package com.MovieTicketBooking.MovieTicketBooking.User;


import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenMovDTO;
import com.MovieTicketBooking.MovieTicketBooking.TicketBooking.BookingRequestDTO;
import com.MovieTicketBooking.MovieTicketBooking.TicketBooking.TicketBookingModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/userdetails")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    //User Registration

    @PostMapping(path = "/userreg")
    public ResponseEntity<?> adduser(@RequestBody UserModel userModel){
        try{
             return userService.userreg(userModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/checkemail")
    public ResponseEntity<String> checkEmail(@RequestParam String userEmail) {
        if (userService.isEmailAlreadyRegistered(userEmail)) {
            return new ResponseEntity<>("Email already registered",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("email available",HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public ResponseEntity<String> getuser(@RequestParam Integer userId) {
        return userService.getUser(userId);
    }


    //user login
    @PostMapping(path = "/userlogin")
    public ResponseEntity<?> loginuser(@RequestBody UserLoginDto userLoginDto){
        try {
            return userService.userlogin(userLoginDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //forgot password
    @PutMapping(path = "/resetpasswordUser")
    public ResponseEntity<?> restpass(@RequestBody UserLoginDto userLoginDto){
        try{
            return userService.resetuserpassword(userLoginDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //User search movie by name
    @GetMapping(path = "/searchmovie")
    public ResponseEntity<List<MovieModel>> search(@RequestParam String movieName) {
        return userService.searchmovie(movieName);
    }

    @GetMapping(path = "/getmovie")
    public ResponseEntity<List<MovieModel>> search(@RequestParam Integer movieId) {
        return userService.getmovie(movieId);
    }

    //Get all movies
    @Transactional
    @GetMapping(path = "/getallmovies")
    public ResponseEntity<List<MovieModel>> allmovies(){
        return userService.getallmovies();
    }

    //get all details when movieid is passed


    @GetMapping("/getmovietheatre")
    public List<TheatreScreenMovDTO> getScreensByMovieId(@RequestParam Integer movieId) {
        return userService.getTheatreScreensByMovieId(movieId);
    }

    @PostMapping("/bookticket")
    public ResponseEntity<TicketBookingModel> bookTicket(@RequestBody TicketBookingModel booking) {
        TicketBookingModel savedBooking = userService.bookTicket(booking);
        return ResponseEntity.ok(savedBooking);
    }

//    @PostMapping("/bookingreq")
//    public ResponseEntity<TicketBookingModel> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
//        TicketBookingModel savedBooking = userService.createBooking(bookingRequestDTO);
//        return ResponseEntity.ok(savedBooking);
//    }


}
