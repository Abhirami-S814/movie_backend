package com.MovieTicketBooking.MovieTicketBooking.Theatre;

import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesDto;
import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieDates.MovieDatesRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreRepo;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangrepo;
import com.MovieTicketBooking.MovieTicketBooking.SeatAvailability.SeatAvailabilityRepo;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeDto;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenMovDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenRepo;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCategory.TicketCategoryRepo;
import com.MovieTicketBooking.MovieTicketBooking.TicketCharge.TicketChargeDto;
import com.MovieTicketBooking.MovieTicketBooking.TicketCharge.TicketChargeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCharge.TicketChargeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    TicketCategoryRepo ticketCategoryRepo;

    @Autowired
    TicketChargeRepo ticketChargeRepo;

    @Autowired
    TheatreScreenRepo theatreScreenRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    MovieDatesRepo movieDatesRepo;

    @Autowired
    SeatAvailabilityRepo seatAvailabilityRepo;

    public ResponseEntity<?> addtheatre(TheatreModel theatreModel) {
        TheatreModel theatreModel1 = new TheatreModel();
            theatreModel1.setName(theatreModel.getName());
            theatreModel1.setEmail(theatreModel.getEmail());
            theatreModel1.setPassword(theatreModel.getPassword());
            theatreModel1.setLocation(theatreModel.getLocation());
            theatreModel1.setContact(theatreModel.getContact());
            theatreModel1.setNoOfScreens(theatreModel.getNoOfScreens());
            theatreRepo.save(theatreModel1);
            return new ResponseEntity<>(theatreModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> theatrelogin(TheatreLoginDto theatreLoginDto) {
        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findByEmailAndPassword(
                theatreLoginDto.getEmail(),
                theatreLoginDto.getPassword()
        );

        if (optionalTheatreModel.isPresent()) {
            TheatreModel theatre = optionalTheatreModel.get();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("theatreId", theatre.getTheatreId()); // assuming getId() returns the primary key
            response.put("name", theatre.getName());    // optionally send other info

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Invalid email or password");

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
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



    public boolean checkTheatreExists(Integer theater1) {
        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theater1);
            return optionalTheatreModel.isPresent();
    }


    public ResponseEntity<?> addShowTime(ShowTimeModel showTimeModel) {
        Integer theatreId = showTimeModel.getTheatreId();

            TheatreModel theatreModel = theatreRepo.findById(theatreId)
                    .orElseThrow(() -> new RuntimeException("Theatre not found with ID: " + theatreId));

            ShowTimeModel newShowTime = new ShowTimeModel();
            newShowTime.setShowStart(showTimeModel.getShowStart());
            newShowTime.setShowEnd(showTimeModel.getShowEnd());
            newShowTime.setDateId(showTimeModel.getDateId());
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

    public ResponseEntity<?> updateShow(Integer showtimeId, LocalTime showStart, LocalTime showEnd) {
        Optional<ShowTimeModel> optionalShowTimeModel = showTimeRepo.findById(showtimeId);
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

            // Fetch Theatre details
            Integer theatreId = showTime.getTheatreId();
            if (theatreId != null) {
                TheatreModel theatre = theatreRepo.findById(theatreId).orElse(null);
                if (theatre != null) {
                    showTimeDto.setTheatreId(theatre.getTheatreId());
                    showTimeDto.setName(theatre.getName());
                }
            }

            // Fetch Movie Dates details
            Integer dateId = showTime.getDateId();
            if (dateId != null) {
                MovieDatesModel movieDatesModel = movieDatesRepo.findById(dateId).orElse(null);
                if (movieDatesModel != null) {
                    showTimeDto.setDateId(movieDatesModel.getDateId());
                    showTimeDto.setMovStart(movieDatesModel.getMovStart());
                    showTimeDto.setMovEnd(movieDatesModel.getMovEnd());
                }
            }

            showTimeDtoList.add(showTimeDto);
        }

        return new ResponseEntity<>(showTimeDtoList, HttpStatus.OK);
    }


    public ResponseEntity<List<TheatreScreenMovDTO>> getTheatreshow(Integer theatreId) {
        List<ShowTimeModel> showTimes = showTimeRepo.findByTheatreId(theatreId);
        List<TheatreScreenMovDTO> dtoList = new ArrayList<>();

        for (ShowTimeModel showTime : showTimes) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();

            // Set ShowTime Details
            dto.setShowTimeId(showTime.getShowtimeId());
            dto.setShowStart(showTime.getShowStart());
            dto.setShowEnd(showTime.getShowEnd());

            // Set Theatre Details
            TheatreModel theatre = theatreRepo.findById(theatreId).orElse(null);
            if (theatre != null) {
                dto.setTheatreId(theatre.getTheatreId());
                dto.setName(theatre.getName());
            }

            // Fetch Movie Details from MovieDatesModel
            Integer dateId = showTime.getDateId();
            if (dateId != null) {
                MovieDatesModel movieDatesModel = movieDatesRepo.findById(dateId).orElse(null);
                if (movieDatesModel != null) {
                    dto.setDateId(movieDatesModel.getDateId());
                    dto.setMovStart(movieDatesModel.getMovStart());
                    dto.setMovEnd(movieDatesModel.getMovEnd());

                    // Fetch Movie Details using movieId
                    Integer movieId = movieDatesModel.getMovieId(); // Ensure MovieDatesModel has movieId
                    if (movieId != null) {
                        MovieModel movie = movieRepo.findById(movieId).orElse(null);
                        if (movie != null) {
                            dto.setMovieId(movie.getMovieId());
                            dto.setMovieName(movie.getMovieName());
                        }
                    }
                }
            }

            // Fetch Screen Details using Theatre ID
            List<TheatreScreenModel> screens = theatreScreenRepo.findByTheatreId(theatreId);
            if (!screens.isEmpty()) {
                TheatreScreenModel screen = screens.get(0); // Assuming first screen for simplicity
                dto.setScreenId(screen.getScreenId());
                dto.setScreenName(screen.getScreenName());
                dto.setSeatCapacity(screen.getSeatCapacity());
            }

            dtoList.add(dto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }





    public ResponseEntity<?> addcate(TicketCategoryModel ticketCategoryModel) {
        TicketCategoryModel ticketCategoryModel1 = new TicketCategoryModel();
        ticketCategoryModel1.setTicketCate(ticketCategoryModel.getTicketCate());
        ticketCategoryRepo.save(ticketCategoryModel1);
        return new ResponseEntity<>(ticketCategoryModel1,HttpStatus.OK);
    }


    public ResponseEntity<?> deletecate(Integer ticketCateId) {
    Optional<TicketCategoryModel> optionalTicketCategoryModel = ticketCategoryRepo.findById(ticketCateId);
    if (optionalTicketCategoryModel.isPresent()){
            TicketCategoryModel ticketCategoryModel = optionalTicketCategoryModel.get();
            ticketCategoryRepo.delete(ticketCategoryModel);
            return new ResponseEntity<>("Ticket category deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updatecate(Integer ticketCateId,String ticketCate) {
        Optional<TicketCategoryModel> optionalTicketCategoryModel = ticketCategoryRepo.findById(ticketCateId);
        if (optionalTicketCategoryModel.isPresent()){
            TicketCategoryModel ticketCategoryModel = optionalTicketCategoryModel.get();
            ticketCategoryModel.setTicketCate(ticketCate);
            ticketCategoryRepo.save(ticketCategoryModel);
            return new ResponseEntity<>("Ticket category updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<TicketCategoryModel>> getcate() {
        List<TicketCategoryModel> ticketCategoryModels = ticketCategoryRepo.findAll();
        return new ResponseEntity<>(ticketCategoryModels,HttpStatus.OK);
    }

    public ResponseEntity<?> addcharge(TicketChargeModel ticketChargeModel) {
        TicketChargeModel ticketChargeModel1 = new TicketChargeModel();
        ticketChargeModel1.setTicketCateId(ticketChargeModel.getTicketCateId());
        ticketChargeModel1.setCharge(ticketChargeModel.getCharge());
        ticketChargeRepo.save(ticketChargeModel1);
        return new ResponseEntity<>(ticketChargeModel1,HttpStatus.OK);
    }

    public ResponseEntity<?> deletecharge(Integer ticketCateId, Integer theatreId) {
        Optional<TicketChargeModel> optionalTicketChargeModel = ticketChargeRepo.findByTicketCateIdAndTheatreId(ticketCateId,theatreId);
            if (optionalTicketChargeModel.isPresent()){
                TicketChargeModel ticketChargeModel = optionalTicketChargeModel.get();
                ticketChargeRepo.delete(ticketChargeModel);
                return new ResponseEntity<>("Ticket charge deleted succesfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("NOT FOUND",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updatecharge(Integer ticketCateId, Integer theatreId,Long charge) {
        Optional<TicketChargeModel> optionalTicketChargeModel = ticketChargeRepo.findByTicketCateIdAndTheatreId(ticketCateId,theatreId);
        if (optionalTicketChargeModel.isPresent()){
            TicketChargeModel ticketChargeModel = optionalTicketChargeModel.get();
            ticketChargeModel.setCharge(charge);
            ticketChargeRepo.save(ticketChargeModel);
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND",HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<TicketChargeDto>> getchargedetails(Integer theatreId) {

        List<TheatreModel> theatreModels = theatreRepo.findByTheatreId(theatreId);
        if (theatreModels == null || theatreModels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TheatreModel theatreModel = theatreModels.get(0);

        List<TicketChargeModel> ticketChargeModels = ticketChargeRepo.findByTheatreId(theatreId);


        List<TicketChargeDto> ticketChargeDtoList = new ArrayList<>();
        for (TicketChargeModel ticketChargeModel : ticketChargeModels) {
            TicketChargeDto ticketChargeDto = new TicketChargeDto();

            ticketChargeDto.setTicketCateId(ticketChargeModel.getTicketCateId());
            ticketChargeDto.setChargeId(ticketChargeModel.getChargeId());
            ticketChargeDto.setCharge(ticketChargeModel.getCharge());

            TicketCategoryModel ticketCategoryModel = ticketCategoryRepo.findById(ticketChargeModel.getTicketCateId()).orElse(null);

            if (ticketCategoryModel != null) {
                ticketChargeDto.setTicketCate(ticketCategoryModel.getTicketCate());
            } else {
                ticketChargeDto.setTicketCate("Unknown Category");
            }

            ticketChargeDto.setTheatreId(theatreId);
            ticketChargeDto.setTheatreName(theatreModel.getName());

            ticketChargeDtoList.add(ticketChargeDto);
        }

        return new ResponseEntity<>(ticketChargeDtoList, HttpStatus.OK);
    }


    public ResponseEntity<?> addScreenmovie(Integer theatreId, TheatreScreenModel theatreScreenModel) {
        boolean theatreExists = theatreRepo.existsById(theatreId);

        if (!theatreExists) {
            return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
        }

        List<TheatreScreenModel> theatreScreenModels = new ArrayList<>();

            TheatreScreenModel newScreen = new TheatreScreenModel();
            newScreen.setScreenName(theatreScreenModel.getScreenName());
            newScreen.setSeatCapacity(theatreScreenModel.getSeatCapacity());
            newScreen.setTheatreId(theatreId);
            theatreScreenModels.add(newScreen);

        theatreScreenRepo.saveAll(theatreScreenModels);

        return new ResponseEntity<>(theatreScreenModels, HttpStatus.OK);
    }

    public ResponseEntity<?> deletescreen(Integer theatreId, Integer screenId) {
        Optional<TheatreScreenModel> optionalTheatreScreenModel = theatreScreenRepo.findByTheatreIdAndScreenId(theatreId,screenId);
        if (optionalTheatreScreenModel.isPresent()){
            theatreScreenRepo.delete(optionalTheatreScreenModel.get());
            return new ResponseEntity<>("Screen removed",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }


    public List<TheatreScreenMovDTO> getScreensByTheatreId(Integer theatreId) {
        List<TheatreScreenModel> screens = theatreScreenRepo.findByTheatreId(theatreId);
        List<TheatreScreenMovDTO> responseList = new ArrayList<>();

        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theatreId);
        String theatrename = optionalTheatreModel.get().getName();

        for (TheatreScreenModel screen : screens) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();
            dto.setTheatreId(screen.getTheatreId());
            dto.setName(theatrename);
            dto.setScreenId(screen.getScreenId());
            dto.setScreenName(screen.getScreenName());

            Optional<MovieModel> movieOptional = movieRepo.findById(screen.getScreenId());
            if (movieOptional.isPresent()) {
                MovieModel movie = movieOptional.get();
                dto.setMovieId(movie.getMovieId());
                dto.setMovieName(movie.getMovieName());
            } else {
                dto.setMovieId(null);
                dto.setMovieName("No Movie Assigned");
            }

            responseList.add(dto);
        }

        return responseList;
    }

    @Transactional
    public ResponseEntity<?> addshowDates(MovieDatesModel movieDatesModel) {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate minStartDate = today.plusDays(1); // Earliest allowed start date (tomorrow)
        LocalDate maxStartDate = today.plusDays(5); // Latest allowed start date (within 5 days)
        LocalDate movStartDate = movieDatesModel.getMovStart();
        LocalDate movEndDate = movieDatesModel.getMovEnd();

        // Validation: movStart should be within 1 to 5 days from today
        if (movStartDate.isBefore(minStartDate) || movStartDate.isAfter(maxStartDate)) {
            return new ResponseEntity<>("Movie start date must be within the next 5 days", HttpStatus.BAD_REQUEST);
        }
        // Validation: movEnd should not be in the past
        if (movEndDate.isBefore(today)) {
            return new ResponseEntity<>("Movie end date cannot be in the past", HttpStatus.BAD_REQUEST);
        }

        Optional<MovieDatesModel> latestMovie = movieDatesRepo.findTopByTheatreIdAndScreenIdOrderByMovEndDesc(
                movieDatesModel.getTheatreId(), movieDatesModel.getScreenId()
        );

        Optional<TheatreScreenModel> optionalTheatreScreenModel =
                theatreScreenRepo.findByTheatreIdAndScreenId(movieDatesModel.getTheatreId(), movieDatesModel.getScreenId());
        Optional<MovieModel> optionalMovieModel = movieRepo.findByMovieId(movieDatesModel.getMovieId());

        if (optionalTheatreScreenModel.isPresent() && optionalMovieModel.isPresent()) {
            MovieDatesModel movieDatesModel1 = new MovieDatesModel();
            movieDatesModel1.setMovStart(movieDatesModel.getMovStart());
            movieDatesModel1.setMovEnd(movieDatesModel.getMovEnd());
            movieDatesModel1.setScreenId(movieDatesModel.getScreenId());
            movieDatesModel1.setMovieId(movieDatesModel.getMovieId());
            movieDatesModel1.setTheatreId(movieDatesModel.getTheatreId());

            movieDatesRepo.save(movieDatesModel1);
            return new ResponseEntity<>(movieDatesModel1, HttpStatus.OK);
        }

        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteshowdate(Integer dateId) {
        Optional<MovieDatesModel> optionalMovieDatesModel = movieDatesRepo.findById(dateId);
        if (optionalMovieDatesModel.isPresent()){
            MovieDatesModel movieDatesModel = optionalMovieDatesModel.get();
            movieDatesRepo.delete(movieDatesModel);
            return new ResponseEntity<>("show date deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateshowdate(Integer dateId, LocalDate movEnd) {
        Optional<MovieDatesModel> optionalMovieDatesModel = movieDatesRepo.findById(dateId);
        if (optionalMovieDatesModel.isPresent()){
            MovieDatesModel movieDatesModel = optionalMovieDatesModel.get();
            movieDatesModel.setMovEnd(movEnd);
            movieDatesRepo.save(movieDatesModel);
            return new ResponseEntity<>("Movie end date updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<MovieDatesDto>> getallshowdate(Integer theatreId) {
        LocalDate today = LocalDate.now();


        List<MovieDatesModel> movieDatesList = movieDatesRepo.findByTheatreId(theatreId);

        List<MovieDatesModel> validMovieDates = movieDatesList.stream()
                .filter(movieDate -> !movieDate.getMovEnd().isBefore(today))
                .collect(Collectors.toList());

        if (validMovieDates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<MovieDatesDto> movieDatesDtoList = validMovieDates.stream().map(movieDate -> {
            MovieDatesDto dto = new MovieDatesDto();
            dto.setDateId(movieDate.getDateId());
            dto.setTheatreId(movieDate.getTheatreId());
            dto.setScreenId(movieDate.getScreenId());
            dto.setMovieId(movieDate.getMovieId());
            dto.setMovStart(movieDate.getMovStart());
            dto.setMovEnd(movieDate.getMovEnd());


            theatreRepo.findById(movieDate.getTheatreId()).ifPresent(theatre -> dto.setName(theatre.getName()));


            theatreScreenRepo.findById(movieDate.getScreenId()).ifPresent(screen -> dto.setScreenName(screen.getScreenName()));


            movieRepo.findById(movieDate.getMovieId()).ifPresent(movie -> dto.setMovieName(movie.getMovieName()));

            return dto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(movieDatesDtoList, HttpStatus.OK);
    }


    public ResponseEntity<?> addMovieToScreen(Integer theatreId, Integer screenId, Integer movieId) {
        Optional<TheatreScreenModel> screenOpt = theatreScreenRepo.findById(screenId);

        if (screenOpt.isPresent() && screenOpt.get().getTheatreId().equals(theatreId)) {
            Optional<MovieModel> movieOpt = movieRepo.findById(movieId);

            if (movieOpt.isPresent()) {
                TheatreScreenModel screen = screenOpt.get();
                MovieModel movie = movieOpt.get();

                TheatreScreenMovDTO responseDto = new TheatreScreenMovDTO();
                responseDto.setTheatreId(theatreId);
                //responseDto.setName("PVR Cinemas");
                responseDto.setScreenName(screen.getScreenName());
                responseDto.setMovieId(movieId);
                responseDto.setMovieName(movie.getMovieName());

                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            }
            return new ResponseEntity<>("Movie not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Screen not found in theatre", HttpStatus.BAD_REQUEST);
    }

    public List<MovieDatesDto> getMoviesWithShowtimes(LocalDate date) {
        List<MovieDatesModel> movieDates = movieDatesRepo.findByMovStartLessThanEqualAndMovEndGreaterThanEqual(date, date);
        List<MovieDatesDto> movieDatesDtoList = new ArrayList<>();

        for (MovieDatesModel model : movieDates) {
            MovieDatesDto dto = new MovieDatesDto();
            dto.setDateId(model.getDateId());
            dto.setTheatreId(model.getTheatreId());
            dto.setScreenId(model.getScreenId());
            dto.setMovieId(model.getMovieId());
            dto.setMovStart(model.getMovStart());
            dto.setMovEnd(model.getMovEnd());
            Optional<TheatreModel> theatre = theatreRepo.findById(model.getTheatreId());
            dto.setName(theatre.isPresent() ? theatre.get().getName() : "Unknown Theatre");

            Optional<TheatreScreenModel> screen = theatreScreenRepo.findById(model.getScreenId());
            dto.setScreenName(screen.isPresent() ? screen.get().getScreenName() : "Unknown Screen");

            Optional<MovieModel> movie = movieRepo.findById(model.getMovieId());
            dto.setMovieName(movie.isPresent() ? movie.get().getMovieName() : "Unknown Movie");

            movieDatesDtoList.add(dto);
        }

        return movieDatesDtoList;
    }





    public ResponseEntity<String> updateScreenCount(Integer theatreId, Integer count) {
        try {
            Optional<TheatreModel> theatreOpt = theatreRepo.findById(theatreId);
            if (theatreOpt.isPresent()) {
                TheatreModel theatre = theatreOpt.get();
                theatre.setNoOfScreens(count);
                theatreRepo.save(theatre);
                return ResponseEntity.ok("Screen count updated successfully.");
            } else {
                return ResponseEntity.status(404).body("Theatre not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    public TheatreScreenModel addMovieScreen(Integer screenId, Integer movieId) throws Exception{
        Optional<TheatreScreenModel> screenOpt = theatreScreenRepo.findById(screenId);
        if (!screenOpt.isPresent()) {
            throw new Exception("Screen with id " + screenId + " not found");
        }

        Optional<MovieModel> movieOpt = movieRepo.findById(movieId);
        if (!movieOpt.isPresent()) {
            throw new Exception("Movie with id " + movieId + " not found");
        }

        TheatreScreenModel screen = screenOpt.get();
        screen.setMovie(movieOpt.get());

        return theatreScreenRepo.save(screen);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<TheatreScreenDTO>> getscreensDTO(Integer theatreId) {
        try {
            List<TheatreScreenModel> theatreScreens = theatreScreenRepo.findByTheatreId(theatreId);

            List<TheatreScreenDTO> dtoList = theatreScreens.stream().map(screen -> {
                TheatreScreenDTO dto = new TheatreScreenDTO();
                dto.setScreenId(screen.getScreenId());
                dto.setScreenName(screen.getScreenName());
                dto.setSeatCapacity(screen.getSeatCapacity());
                dto.setTheatreId(screen.getTheatreId());

                // Avoid triggering lazy loading of LOBs by accessing only safe fields
                if (screen.getMovie() != null) {
                    dto.setMovieName(screen.getMovie().getMovieName()); // only safe fields
                }

                return dto;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // or log
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<TheatreScreenModel>> getScreensmov(Integer theatreId) {
        List<TheatreScreenModel> theatreScreenModels = theatreScreenRepo.findByTheatreId(theatreId);
        for (TheatreScreenModel screen : theatreScreenModels) {
            System.out.println("Screen: " + screen.getScreenName());
            if (screen.getMovie() != null) {
                System.out.println("Movie Assigned: " + screen.getMovie().getMovieName());
            } else {
                System.out.println("No Movie Assigned");
            }
        }

        return new ResponseEntity<>(theatreScreenModels,HttpStatus.OK);
    }


//    public TheatreScreenModel saveScreenWithMovie(TheatreScreenDTO dto) {
//        TheatreScreenModel screen = new TheatreScreenModel();
//
//        // if screenId is provided, update existing screen
//        if (dto.getScreenId() != null) {
//            Optional<TheatreScreenModel> existing = theatreScreenRepo.findById(dto.getScreenId());
//            if (existing.isPresent()) {
//                screen = existing.get();
//            }
//        }
//        screen.setScreenId(dto.getScreenId());
//        screen.setScreenName(dto.getScreenName());
//        screen.setSeatCapacity(dto.getSeatCapacity());
//        screen.setTheatreId(dto.getTheatreId());
//
//        if (dto.getMovieId() != null) {
//            Optional<MovieModel> movie = movieRepo.findById(dto.getMovieId());
//            movie.ifPresent(screen::setMovie);
//        }
//
//        return theatreScreenRepo.save(screen);
//    }



//    public SeatAvailabilityModel availableseats(Integer screenId) {
//            TheatreScreenModel theatreScreenModel = theatreScreenRepo.findById(screenId)
//                    .orElseThrow(() -> new RuntimeException("Screen not found"));
//
//        SeatAvailabilityModel seatAvailability = new SeatAvailabilityModel(screenId, theatreScreenModel.getSeatCapacity());
//            return seatAvailabilityRepo.save(seatAvailability);
//    }
}
