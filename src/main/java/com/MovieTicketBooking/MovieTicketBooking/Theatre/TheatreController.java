package com.MovieTicketBooking.MovieTicketBooking.Theatre;


import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesDto;
import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeDto;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenMovDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreTax.TheatreTaxModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge.TicketCateChargeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCharge.TicketChargeDto;
import com.MovieTicketBooking.MovieTicketBooking.TicketCharge.TicketChargeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<?> addDetails(@RequestBody TheatreModel theatreModel) {
        try {
            return theatreService.addtheatre(theatreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //theatre login
    @PostMapping(path = "/theatrelogin")
    public ResponseEntity<?> login(@RequestBody TheatreLoginDto theatreLoginDto) {
        try {
            return theatreService.theatrelogin(theatreLoginDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //list all theaters
    @GetMapping(path = "/allTheatres")
    public ResponseEntity<List<TheatreModel>> alltheatres() {
        return theatreService.getalltheatres();
    }

    //list theatres by id
    @GetMapping(path = "/gettheatre")
    public ResponseEntity<List<TheatreModel>> theatrebyId(@RequestParam Integer theatreId) {
        return theatreService.theatrebyid(theatreId);
    }



    //-----------------------TICKET CATEGORY------------------------------------------------------------------

    //Add ticket category
    @PostMapping("/addcate")
    public ResponseEntity<?> addcate(@RequestBody TicketCateChargeModel ticketCateChargeModel,
                                     @RequestParam Integer theatreId) {
        try {
            return theatreService.addcate(ticketCateChargeModel,theatreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //remove ticket category
    @DeleteMapping("/deletecate")
    public ResponseEntity<?> deltecate(@RequestParam Integer ticketcatechargeId) {
        try {
            return theatreService.deletecate(ticketcatechargeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update ticket category
    @PutMapping("/updatecate")
    public ResponseEntity<?> updatecate(@RequestParam Integer ticketcatechargeId,
                                        @RequestParam Long ticketcharge) {
        try {
            return theatreService.updatecate(ticketcatechargeId,ticketcharge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Get all categories
    @GetMapping("/getcate")
    public ResponseEntity<List<TicketCateChargeModel>> getcate() {
        return theatreService.getcate();
    }

    //get category by theatre
    @GetMapping(path = "/gettheatrecate")
    public ResponseEntity<List<TicketCateChargeModel>> catetheatre(@RequestParam Integer theatreId) {
        return theatreService.catebytheatre(theatreId);
    }

    //------------------------------THEATRE SCREEN-------------------------------------------------

    //Theatre add  screen
    @PostMapping("/addscreenmov/{theatreId}")
    public ResponseEntity<?> addmovie(@PathVariable Integer theatreId,@RequestBody TheatreScreenModel theatreScreenModel){
        try{
            return theatreService.addScreenmovie(theatreId,theatreScreenModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.OK);
    }

    //Theatre Delete screen
    @Transactional
    @DeleteMapping("/deletescreen")
    public ResponseEntity<?> deleteScreen(@RequestParam Integer theatreId,@RequestParam Integer screenId){
        try{
            return theatreService.deletescreen(theatreId,screenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Get all screens with seat capacity
    @GetMapping("/getscreens")
    public ResponseEntity<List<TheatreScreenDTO>> getscreens(@RequestParam(required = true) Integer theatreId) {
        if (theatreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return theatreService.getscreensDTO(theatreId);
    }

    //if new screen is added the noOfScreens count need to increase
    @PutMapping("/updatescreencount")
    public ResponseEntity<String> updateScreenCount(
            @RequestParam Integer theatreId,
            @RequestParam Integer count) {
        return theatreService.updateScreenCount(theatreId, count);
    }


    // Get all theatre screens with movie(now all is null)
    @GetMapping("/getallmoviescreens")
    public List<TheatreScreenMovDTO> getScreensByTheatreId(@RequestParam Integer theatreId) {
        return theatreService.getScreensByTheatreId(theatreId);
    }
//
//    @GetMapping("/getTheatresByMovie")
//    public ResponseEntity<List<TheatreScreenMovDTO>> getTheatresByMovie(@RequestParam Integer movieId) {
//        List<TheatreScreenMovDTO> result = theatreService.getTheatreMovieDetails(movieId);
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/getmovscreens")
    public ResponseEntity<List<TheatreScreenModel>> getScreensmov(@RequestParam Integer theatreId) {
        return theatreService.getScreensmov(theatreId);
    }

    //ADD MOVIE TO SCREEN OF A THEATRE IF THE SCREEN PRESENT

//    @PostMapping("/addmovietoscreen")
//    public ResponseEntity<?> addMovieToScreen(@RequestParam Integer theatreId,
//                                              @RequestParam Integer screenId,
//                                              @RequestParam Integer movieId) {
//        try {
//            return theatreService.addMovieToScreen(theatreId, screenId, movieId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //Add MOVIE to SCREEN
//    @PostMapping("/addmovie")
//    public TheatreScreenModel addOrUpdateScreenWithMovie(@RequestBody TheatreScreenDTO dto) {
//        return theatreService.saveScreenWithMovie(dto);
//    }
    @PostMapping("/addmovie")
    public ResponseEntity<?> addMovieToScreen(@RequestParam Integer screenId, @RequestParam Integer movieId) {
        try {
            TheatreScreenModel updatedScreen = theatreService.addMovieScreen(screenId, movieId);
            return ResponseEntity.ok(updatedScreen);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletemovie")
    public ResponseEntity<?> deleeteMovie(@RequestParam Integer screenId){
        try{
            return theatreService.deletemovie(screenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/updatemovie")
    public ResponseEntity<?> updatecharge(@RequestParam Integer movieId,
                                          @RequestParam Integer screenId) {
        try {
            return theatreService.updatemovie(movieId,screenId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //-------------------------------------------------------------------------------------------------

    //---------------------------------SHOW DATES--------------------------------------------------

    //Theatre add showstart and showend date
    @PostMapping("/addshowdates")
    public ResponseEntity<?> addDates(@RequestBody MovieDatesModel movieDatesModel){
        try{
            return theatreService.addshowDates(movieDatesModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Theatre delete show
    @DeleteMapping("/deleteshow")
    public ResponseEntity<?> deleeteShowdate(@RequestParam Integer dateId){
        try{
            return theatreService.deleteshowdate(dateId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update show end date
    @PutMapping("updateshowdate")
    public ResponseEntity<?> updateShowdate(@RequestParam Integer dateId, @RequestParam LocalDate movEnd){
        try{
            return theatreService.updateshowdate(dateId,movEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all show dates of screen
    @GetMapping("/getallshowdates")
    public ResponseEntity<List<MovieDatesDto>> getAllShowdates(@RequestParam Integer theatreId) {
        return theatreService.getallshowdate(theatreId);
    }

//--------------------------show date--------------------------------------------------------------

    //----------------------------------SHOW TIME----------------------------------------------------

    //Theatre add showtimes
    @PostMapping(path = "/addshowTime")
    public ResponseEntity<?> addShow(@RequestBody ShowTimeModel showTimeModel) {
        try {
            Integer theater1 = showTimeModel.getTheatreId();
            boolean theatreExists = theatreService.checkTheatreExists(theater1);

            if (!theatreExists) {
                return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
            }

            return theatreService.addShowTime(showTimeModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Theatre delete showtimes
    @DeleteMapping(path = "/deleteShow")
    public ResponseEntity<?> deleteshow(@RequestParam Integer theatreId, @RequestParam Integer showtimeId) {
        try {
            return theatreService.deleteShow(theatreId, showtimeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Theatre update showtime
    @PutMapping(path = "/updateShow")
    public ResponseEntity<?> updateshow(@RequestParam Integer showtimeId,@RequestParam LocalTime showStart,
                                        @RequestParam LocalTime showEnd) {
        try {
            return theatreService.updateShow(showtimeId,showStart,showEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all showtimes
    @GetMapping("/allshow")
    public ResponseEntity<List<ShowTimeDto>> getAllShowTimes() {
        return theatreService.getAllShowTimes();
    }

    //get all showtimes of particular theatre
    @GetMapping("/getshow")
    public ResponseEntity<List<TheatreScreenMovDTO>> getshow(@RequestParam Integer theatreId) {
        List<TheatreScreenMovDTO> result = theatreService.getTheatreshow(theatreId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getshowScreen")
    public ResponseEntity<List<TheatreScreenMovDTO>> getshowscreen(@RequestParam Integer screenId) {
        List<TheatreScreenMovDTO> result = theatreService.getScreenshow(screenId);
        return ResponseEntity.ok(result);
    }


    //datesssssss
    @GetMapping("/showtimes")
    public List<MovieDatesDto> getMoviesWithShowtimes(
            @RequestParam(value = "date", required = false) LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        return theatreService.getMoviesWithShowtimes(date);
    }

//    //seat availability
//    @PostMapping("/availableseats")
//    public ResponseEntity<SeatAvailabilityModel> addSeats(@RequestParam Integer screenId) {
//        return ResponseEntity.ok(theatreService.availableseats(screenId));
//    }



    @PostMapping("/addtx")
    public ResponseEntity<?> addcate(@RequestBody TheatreTaxModel theatreTaxModel,
                                     @RequestParam Integer theatreId) {
        try {
            return theatreService.addtax(theatreTaxModel,theatreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}