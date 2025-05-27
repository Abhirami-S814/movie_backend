package com.MovieTicketBooking.MovieTicketBooking.User;


import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
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

    //Get all movies
    @Transactional
    @GetMapping(path = "/getallmovies")
    public ResponseEntity<List<MovieModel>> allmovies(){
        return userService.getallmovies();
    }

    

}
