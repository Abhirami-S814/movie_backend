package com.MovieTicketBooking.MovieTicketBooking.Admin;



import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    MovieRepo movieRepo;

    //admin user
    public ResponseEntity<?> addadmin(AdminModel adminModel) {
        AdminModel adminModel1 = new AdminModel();
        adminModel1.setEmail(adminModel.getEmail());
        adminModel1.setPassword(adminModel.getPassword());

        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }


    // admin login
    public ResponseEntity<?> adminlogin(AdminLoginDto adminLoginDto) {
        Optional<AdminModel> optionalAdminModel = adminRepo.findByEmailAndPassword(adminLoginDto.getEmail(),
                                                adminLoginDto.getPassword());
        if (optionalAdminModel.isPresent()){
            return new ResponseEntity<>("Login Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Login failed",HttpStatus.BAD_REQUEST);
        }
    }

    //Add movies
    public ResponseEntity<?> addmovies(MovieModel movieModel) {
        MovieModel movieModel1 = new MovieModel();
        movieModel1.setMovieName(movieModel.getMovieName());
        movieModel1.setLanguage(movieModel.getLanguage());
        movieModel1.setGenre(movieModel.getGenre());
        movieModel1.setDuration(movieModel.getDuration());
        movieModel1.setDescription(movieModel.getDescription());
        movieModel1.setReleaseDate(movieModel.getReleaseDate());

        movieRepo.save(movieModel1);
        return new ResponseEntity<>(movieModel1,HttpStatus.OK);
    }

    //delete movies
    public ResponseEntity<?> deletemovies(Integer movieId) {
        Optional<MovieModel> optionalMovieModel = movieRepo.findById(movieId);
        if (optionalMovieModel.isPresent()){
            MovieModel movieModel = optionalMovieModel.get();
            movieRepo.delete(movieModel);
            return new ResponseEntity<>("Movie Deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Deleteion failed",HttpStatus.BAD_REQUEST);
        }
    }

    //update movie name and description
    public ResponseEntity<?> updateMovieNameAndDes(Integer movieId,String movieName, String description) {
        Optional<MovieModel> optionalMovieModel = movieRepo.findById(movieId);
        if (optionalMovieModel.isPresent()){
            MovieModel movieModel = optionalMovieModel.get();
            movieModel.setMovieName(movieName);
            movieModel.setDescription(description);
            movieRepo.save(movieModel);
            return  new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Movie not found",HttpStatus.NOT_FOUND);
        }
    }

    //update movie language and genre
    public ResponseEntity<?> updateMovieLangAndGenre(Integer movieId,Integer language, Integer genre) {
        Optional<MovieModel> optionalMovieModel = movieRepo.findById(movieId);
        if (optionalMovieModel.isPresent()){
            MovieModel movieModel = optionalMovieModel.get();
            movieModel.setLanguage(language);
            movieModel.setGenre(genre);
            movieRepo.save(movieModel);
            return  new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Movie not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateAdmin(String email, String password) {
        Optional<AdminModel> optionalAdminModel = adminRepo.findByEmail(email);
        if (optionalAdminModel.isPresent()){
            AdminModel adminModel = optionalAdminModel.get();
            adminModel.setPassword(password);
            adminRepo.save(adminModel);
            return new ResponseEntity<>(adminModel,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Admin not found",HttpStatus.NOT_FOUND);
        }
    }
}
