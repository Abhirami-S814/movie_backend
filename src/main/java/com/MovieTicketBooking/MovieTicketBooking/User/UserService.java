package com.MovieTicketBooking.MovieTicketBooking.User;



import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MovieRepo movieRepo;

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
            return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
        } else {
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

    public ResponseEntity<?> resetuserpassword(String email,String password) {
        Optional<UserModel> optionalUserModel = userRepo.findByEmail(email);
        if (optionalUserModel.isPresent()){
            UserModel userModel = optionalUserModel.get();
            userModel.setPassword(password);
            userRepo.save(userModel);
            return new ResponseEntity<>(userModel,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }
}
