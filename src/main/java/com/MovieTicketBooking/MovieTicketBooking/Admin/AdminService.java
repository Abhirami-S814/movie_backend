package com.MovieTicketBooking.MovieTicketBooking.Admin;



import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    MovieLangrepo movieLangrepo;

    @Autowired
    MovieGenreRepo movieGenreRepo;

    //admin user
    public ResponseEntity<?> addadmin(AdminModel adminModel) {
        AdminModel adminModel1 = new AdminModel();
        adminModel1.setEmail(adminModel.getEmail());
        adminModel1.setPassword(adminModel.getPassword());

        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }


    // admin login
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASSWORD = "admin123";

    public boolean adminLogin(AdminLoginDto adminLoginDto) {
        return ADMIN_EMAIL.equals(adminLoginDto.getEmail()) && ADMIN_PASSWORD.equals(adminLoginDto.getPassword());
    }

    //Add movies
    public ResponseEntity<?> addmovies(MovieModel movieModel, MultipartFile movieposter) throws IOException {
        MovieModel movieModel1 = new MovieModel();
        movieModel1.setMovieName(movieModel.getMovieName());
        movieModel1.setLanguage(movieModel.getLanguage());
        movieModel1.setGenre(movieModel.getGenre());
        movieModel1.setDuration(movieModel.getDuration());
        movieModel1.setDescription(movieModel.getDescription());
        movieModel1.setReleaseDate(movieModel.getReleaseDate());
        movieModel1.setMovieposter(movieposter.getBytes());

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



    public ResponseEntity<?> addlang(MovieLangModel movieLangModel) {
        MovieLangModel movieLangModel1 = new MovieLangModel();
        movieLangModel1.setLangName(movieLangModel.getLangName());
        movieLangrepo.save(movieLangModel1);
        return new ResponseEntity<>(movieLangModel1,HttpStatus.OK);
    }

    public ResponseEntity<?> deletelang(Integer langId) {
        Optional<MovieLangModel> optionalMovieLangModel = movieLangrepo.findById(langId);
        if (optionalMovieLangModel.isPresent()){
            MovieLangModel movieLangModel = optionalMovieLangModel.get();
            movieLangrepo.delete(movieLangModel);
            return new ResponseEntity<>("Language Deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Deleteion failed",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updatelang(Integer langId, String langName) {
        Optional<MovieLangModel> optionalMovieLangModel = movieLangrepo.findById(langId);
        if (optionalMovieLangModel.isPresent()){
            MovieLangModel movieLangModel = optionalMovieLangModel.get();
            movieLangModel.setLangName(langName);
            movieLangrepo.save(movieLangModel);
            return new ResponseEntity<>(movieLangModel,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Language not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<MovieLangModel>> allang() {
        List<MovieLangModel> movieLangModels = movieLangrepo.findAll();
        return new ResponseEntity<>(movieLangModels,HttpStatus.OK);
    }

    public ResponseEntity<?> addgenre(MovieGenreModel movieGenreModel) {
        MovieGenreModel movieGenreModel1 = new MovieGenreModel();
        movieGenreModel1.setGenreName(movieGenreModel.getGenreName());
        movieGenreRepo.save(movieGenreModel1);
        return new ResponseEntity<>(movieGenreModel1,HttpStatus.OK);
    }


    public ResponseEntity<?> deletegenre(Integer genreId) {
        Optional<MovieGenreModel> optionalMovieGenreModel = movieGenreRepo.findById(genreId);
        if (optionalMovieGenreModel.isPresent()){
            MovieGenreModel movieGenreModel = optionalMovieGenreModel.get();
            movieGenreRepo.delete(movieGenreModel);
            return new ResponseEntity<>("genre Deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Deleteion failed",HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> updategenre(Integer genreId, String genreName) {
        Optional<MovieGenreModel> optionalMovieGenreModel = movieGenreRepo.findById(genreId);
        if (optionalMovieGenreModel.isPresent()){
            MovieGenreModel movieGenreModel = optionalMovieGenreModel.get();
            movieGenreModel.setGenreName(genreName);
            movieGenreRepo.save(movieGenreModel);
            return new ResponseEntity<>(movieGenreModel,HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<MovieGenreModel>> getgenre() {
        try {
            List<MovieGenreModel> movieGenreModels = movieGenreRepo.findAll();

            return new ResponseEntity<>(movieGenreModels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
