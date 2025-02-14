package com.MovieTicketBooking.MovieTicketBooking.Theatre;

import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangrepo;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeDto;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    TheatreRepo theatreRepo;

    @Autowired
    MovieLangrepo movieLangrepo;

    @Autowired
    MovieGenreRepo movieGenreRepo;


    @Autowired
    ShowTimeRepo showTimeRepo;

    public ResponseEntity<?> addtheatre(TheatreModel theatreModel) {
        TheatreModel theatreModel1 = new TheatreModel();
            theatreModel1.setName(theatreModel.getName());
            theatreModel1.setEmail(theatreModel.getEmail());
            theatreModel1.setPassword(theatreModel.getPassword());
            theatreModel1.setLocation(theatreModel.getLocation());
            theatreModel1.setContact(theatreModel.getContact());
            theatreRepo.save(theatreModel1);
            return new ResponseEntity<>(theatreModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> theatrelogin(TheatreLoginDto theatreLoginDto) {
        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findByEmailAndPassword(theatreLoginDto.getEmail(),
                theatreLoginDto.getPassword());
            if (optionalTheatreModel.isPresent()){
                return new ResponseEntity<>("Login successfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Login failed",HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<List<TheatreModel>> getalltheatres() {
        List<TheatreModel> theatreModels = theatreRepo.findAll();
            return new ResponseEntity<>(theatreModels,HttpStatus.OK);
    }

    public ResponseEntity<List<TheatreModel>> theatrebyid(Integer theatreId) {
        List<TheatreModel> theatreModels = theatreRepo.findByTheatreId(theatreId);
            return new ResponseEntity<>(theatreModels,HttpStatus.OK);
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
                return new ResponseEntity<>(movieGenreModel,HttpStatus.OK);
            }
            return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<MovieGenreModel>> getgenre() {
        List<MovieGenreModel> movieGenreModels = movieGenreRepo.findAll();
            return new ResponseEntity<>(movieGenreModels,HttpStatus.OK);
    }


    public boolean checkTheatreExists(Integer theater1) {
        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theater1);
            return optionalTheatreModel.isPresent();
    }


    public ResponseEntity<?> addShowTime(ShowTimeModel showTimeModel) {
        Integer theatreId = showTimeModel.getTheatreId();

            TheatreModel theatreModel = theatreRepo.findById(theatreId)
                    .orElseThrow(() -> new RuntimeException("Theatre not found with ID: " + theatreId));

            // Creating new ShowTimeModel object
            ShowTimeModel newShowTime = new ShowTimeModel();
            newShowTime.setShowStart(showTimeModel.getShowStart());
            newShowTime.setShowEnd(showTimeModel.getShowEnd());
            newShowTime.setTheatreId(theatreId);

            showTimeRepo.save(newShowTime);

            return new ResponseEntity<>(newShowTime, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteShow(Integer theatreId, Integer showtimeId) {
        Optional<ShowTimeModel> optionalShowTimeModel = showTimeRepo.findByTheatreIdAndShowtimeId(theatreId,showtimeId);
            if (optionalShowTimeModel.isPresent()){
                ShowTimeModel showTimeModel = optionalShowTimeModel.get();
                showTimeRepo.delete(showTimeModel);
                return new ResponseEntity<>("Show time deleted",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Deletion failed",HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<?> updateShow(Integer theatreId, Integer showtimeId,
                                        LocalTime showStart, LocalTime showEnd) {
        Optional<ShowTimeModel> optionalShowTimeModel = showTimeRepo.findByTheatreIdAndShowtimeId(theatreId, showtimeId);
            if (optionalShowTimeModel.isPresent()){
                ShowTimeModel showTimeModel = optionalShowTimeModel.get();
                showTimeModel.setShowStart(showStart);
                showTimeModel.setShowEnd(showEnd);
                showTimeRepo.save(showTimeModel);
                return new ResponseEntity<>(showTimeModel,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Showtime not found",HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<List<ShowTimeDto>> getAllShowTimes() {
        List<ShowTimeModel> showTimes = showTimeRepo.findAll();
        List<ShowTimeDto> showTimeDtoList = new ArrayList<>();

        for (ShowTimeModel showTime : showTimes) {
            ShowTimeDto showTimeDto = new ShowTimeDto();
            showTimeDto.setShowTimeId(showTime.getShowtimeId());
            showTimeDto.setShowStart(showTime.getShowStart());
            showTimeDto.setShowEnd(showTime.getShowEnd());

            Integer theatreId = showTime.getTheatreId();
            if (theatreId != null) {
                TheatreModel theatre = theatreRepo.findById(theatreId).orElse(null);
                if (theatre != null) {
                    showTimeDto.setTheatreId(theatre.getTheatreId());
                    showTimeDto.setName(theatre.getName());
                }
            }

            showTimeDtoList.add(showTimeDto);
        }

        return new ResponseEntity<>(showTimeDtoList,HttpStatus.OK);
    }

    public ResponseEntity<List<ShowTimeDto>> getTheatreshow(Integer theatreId) {
        List<ShowTimeModel> showTimes = showTimeRepo.findByTheatreId(theatreId);
        List<ShowTimeDto> showTimeDtoList = new ArrayList<>();

        for (ShowTimeModel showTime : showTimes) {
            ShowTimeDto showTimeDto = new ShowTimeDto();
            showTimeDto.setShowTimeId(showTime.getShowtimeId());
            showTimeDto.setShowStart(showTime.getShowStart());
            showTimeDto.setShowEnd(showTime.getShowEnd());
            showTimeDto.setTheatreId(theatreId);
            TheatreModel theatre = theatreRepo.findById(theatreId).orElse(null);
            if (theatre != null) {
                showTimeDto.setTheatreId(theatre.getTheatreId());
                showTimeDto.setName(theatre.getName());
            }

            showTimeDtoList.add(showTimeDto);
        }

        return new ResponseEntity<>(showTimeDtoList,HttpStatus.OK);
    }


    //-------------------------------------------------------------------------------------------------------
    //        //----------------------------------------------------------------------------------------------
//
////    public void sendVerificationEmail(String email) throws Exception {
////        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findByEmail(email);
////        if (optionalTheatreModel.isPresent()) {
////            TheatreModel theatreModel = optionalTheatreModel.get();
////            String resetLink = "http://yourdomain.com/resetPassword?email=" + email; // Build reset link
////            emailService.sendResetPasswordEmail(email, resetLink);  // Call EmailService to send email
////        } else {
////            throw new Exception("Theatre not found");
////        }
//    }
//
//
////    public ResponseEntity<?> updatetheatrepass(String email, String password) {
////        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findByEmail(email);
////        if (optionalTheatreModel.isPresent()){
////            TheatreModel theatreModel = optionalTheatreModel.get();
////            theatreModel.setPassword(password);
////            theatreRepo.save(theatreModel);
////            return new ResponseEntity<>(theatreModel,HttpStatus.OK);
////        }
////        else {
////            return new ResponseEntity<>("Theatre not found",HttpStatus.NOT_FOUND);
////        }
////    }
//
//    //-----------------------------------------------------------------------------------------------------------
//
////    public TicketCategoryModel addTicketCategory(Integer theatreId, TicketCategoryModel ticketCategory) {
////        Optional<TheatreModel> theatreModel = theatreRepo.findById(theatreId);
////        if (theatreModel.isPresent()) {
////            ticketCategory.setTheatreModel(theatreModel.get());
////            return ticketCategoryRepo.save(ticketCategory);
////        } else {
////            throw new RuntimeException("Theatre not found");
////        }
////    }
}
