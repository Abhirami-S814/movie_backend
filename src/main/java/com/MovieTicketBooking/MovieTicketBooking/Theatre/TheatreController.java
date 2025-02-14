package com.MovieTicketBooking.MovieTicketBooking.Theatre;


import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeDto;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/theatredetails")
@CrossOrigin
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    //theatre Regtn
    @PostMapping(path = "/theatreregtn")
    public ResponseEntity<?> adddetails(@RequestBody TheatreModel theatreModel){
        try{
            return theatreService.addtheatre(theatreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //theatre login
    @PostMapping(path = "/theatrelogin")
    public ResponseEntity<?> login(@RequestBody TheatreLoginDto theatreLoginDto){
        try{
            return theatreService.theatrelogin(theatreLoginDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //list all theaters
    @GetMapping(path = "/allTheatres")
    public ResponseEntity<List<TheatreModel>> alltheatres(){
        return theatreService.getalltheatres();
    }

    //list theatres by id
    @GetMapping(path = "/gettheathre")
    public ResponseEntity<List<TheatreModel>> theatrebyId(@RequestParam Integer theatreId){
        return theatreService.theatrebyid(theatreId);
    }

    //add movie languages
    @PostMapping(path = "/addlang")
    public ResponseEntity<?> addlang(@RequestBody MovieLangModel movieLangModel){
        return theatreService.addlang(movieLangModel);
    }

    //delete movie language
    @DeleteMapping(path = "/deletelang")
    public ResponseEntity<?> deletelang(@RequestParam Integer langId){
        try{
            return theatreService.deletelang(langId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Update movie language
    @PutMapping(path = "/updatelang")
    public ResponseEntity<?> updatelang(@RequestParam Integer langId,@RequestParam String langName){
        try{
            return theatreService.updatelang(langId,langName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all movie languages
    @GetMapping(path = "/getallang")
    public ResponseEntity<List<MovieLangModel>> allang(){
        return theatreService.allang();
    }

    //add movie genre
    @PostMapping(path = "/addgenre")
    public ResponseEntity<?> addgenre(@RequestBody MovieGenreModel movieGenreModel){
        try{
            return theatreService.addgenre(movieGenreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //delete movie genre
    @DeleteMapping(path = "/deletegenre")
    public ResponseEntity<?> deletegenre(@RequestParam Integer genreId){
        try{
          return theatreService.deletegenre(genreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update movie genre
    @PutMapping(path = "/updategenre")
    public ResponseEntity<?> updategenre(@RequestParam Integer genreId,@RequestParam String genreName){
        try{
            return theatreService.updategenre(genreId,genreName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all genres
    @GetMapping(path = "/getgenre")
    public ResponseEntity<List<MovieGenreModel>> getgenre(){
        return theatreService.getgenre();
    }

    //Theatre add showtimes
    @PostMapping(path = "/addshowTime")
    public ResponseEntity<?> addShow(@RequestBody ShowTimeModel showTimeModel) {
        try {
            Integer theater1 = showTimeModel.getTheatreId();
            //System.out.println("TheatreId",theatre1);
            boolean theatreExists = theatreService.checkTheatreExists(theater1);

            if (!theatreExists) {
                return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
            }

            showTimeModel.setTheatreId(showTimeModel.getTheatreId());
            return theatreService.addShowTime(showTimeModel);
            //return new ResponseEntity<>(savedShowtime, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Theatre delete showtimes
    @DeleteMapping(path = "/deleteShow")
    public ResponseEntity<?> deleteshow(@RequestParam Integer theatreId,@RequestParam Integer showtimeId){
        try{
            return theatreService.deleteShow(theatreId,showtimeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Theatre update showtime
    @PutMapping(path = "/updateShow")
    public ResponseEntity<?> updateshow(@RequestParam Integer theatreId, @RequestParam Integer showtimeId,
                                        @RequestParam LocalTime showStart, @RequestParam LocalTime showEnd){
        try{
            return theatreService.updateShow(theatreId,showtimeId,showStart,showEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all showtimes
    @GetMapping("/allshow")
    public ResponseEntity<List<ShowTimeDto>> getAllShowTimes() {
        return theatreService.getAllShowTimes();
    }

    //get all showtimes of particular theatre
    @GetMapping("/getshow")
    public ResponseEntity<List<ShowTimeDto>> getshow(@RequestParam Integer theatreId){
        return theatreService.getTheatreshow(theatreId);
    }


////---------TICKET CATEGORY BY THEATRE
//    @PostMapping("/{theatreId}/ticketCategories")
//    public TicketCategoryModel addTicketCategory(@PathVariable Integer theatreId, @RequestBody TicketCategoryModel ticketCategoryModel) {
//        return theatreService.addTicketCategory(theatreId, ticketCategoryModel);
//    }
//
//
//    // RESET password
//
//    @PutMapping("/sendVerificationEmail")
//    public ResponseEntity<?> sendVerificationEmail(@RequestParam String email) {
//        try {
//            theatreService.sendVerificationEmail(email);  // Call the service to send the email
//            return new ResponseEntity<>("Verification email sent", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping(path = "/resetpassword")
//    public ResponseEntity<?> updatetheatre(@RequestParam String email,@RequestParam String password){
//        try{
//            return theatreService.updatetheatrepass(email,password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
