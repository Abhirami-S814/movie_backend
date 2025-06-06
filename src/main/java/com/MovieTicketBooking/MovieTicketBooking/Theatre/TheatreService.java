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
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowDTO;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeDto;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeModel;
import com.MovieTicketBooking.MovieTicketBooking.ShowTime.ShowTimeRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenMovDTO;
import com.MovieTicketBooking.MovieTicketBooking.TheatreScreen.TheatreScreenRepo;
import com.MovieTicketBooking.MovieTicketBooking.TheatreTax.TheatreTaxModel;
import com.MovieTicketBooking.MovieTicketBooking.TheatreTax.TheatreTaxRepo;
import com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge.TicketCateChargeModel;
import com.MovieTicketBooking.MovieTicketBooking.TicketCateCharge.TicketcateChargeRepo;
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

import java.time.Duration;
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

    @Autowired
    TicketcateChargeRepo ticketcateChargeRepo;

    @Autowired
    TheatreTaxRepo theatreTaxRepo;

    public ResponseEntity<?> addtheatre(TheatreModel theatreModel) {
        TheatreModel theatreModel1 = new TheatreModel();
            theatreModel1.setName(theatreModel.getName());
            theatreModel1.setEmail(theatreModel.getEmail());
            theatreModel1.setPassword(theatreModel.getPassword());
            theatreModel1.setLatitude(theatreModel.getLatitude());
            theatreModel1.setLongitude(theatreModel.getLongitude());
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


    @Transactional
    public ResponseEntity<?> addShowTime(ShowTimeModel showTimeModel) {
        try {
            // Check if Theatre exists
            Integer theatreId = showTimeModel.getTheatreId();
            boolean theatreExists = checkTheatreExists(theatreId);
            if (!theatreExists) {
                return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
            }

            // Fetch Movie and validate existence
            Integer movieId = showTimeModel.getMovieId();
            Optional<MovieModel> movieOpt = movieRepo.findByMovieId(movieId);

            if (movieOpt.isEmpty()) {
                return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
            }

            MovieModel movie = movieOpt.get();

            // Get movie duration directly as LocalTime
            LocalTime movieDurationTime = movie.getDuration(); // Already a LocalTime
            Duration movieDuration = Duration.ofHours(movieDurationTime.getHour())
                    .plusMinutes(movieDurationTime.getMinute())
                    .plusSeconds(movieDurationTime.getSecond());

            // Get and compare show time duration
            LocalTime startTime = showTimeModel.getShowStart();

            if (startTime == null) {
                return new ResponseEntity<>("Show start and end time are required", HttpStatus.BAD_REQUEST);
            }



            showTimeRepo.save(showTimeModel);
            return new ResponseEntity<>(showTimeModel, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong while adding show time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @Transactional
    public List<TheatreScreenMovDTO> getTheatreshow(Integer theatreId) {
        List<TheatreScreenModel> screens = theatreScreenRepo.findByTheatreId(theatreId);
        List<TheatreScreenMovDTO> responseList = new ArrayList<>();

        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theatreId);
        String theatrename = optionalTheatreModel.map(TheatreModel::getName).orElse("Unknown Theatre");

        for (TheatreScreenModel screen : screens) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();
            dto.setTheatreId(screen.getTheatreId());
            dto.setName(theatrename);
            dto.setScreenId(screen.getScreenId());
            dto.setScreenName(screen.getScreenName());
            dto.setSeatCapacity(screen.getSeatCapacity());

            // Movie info
            MovieModel movie = screen.getMovie();
            if (movie != null) {
                dto.setMovieId(movie.getMovieId());
                dto.setMovieName(movie.getMovieName());

                // Movie Dates
                movieDatesRepo.findByScreenId(screen.getScreenId()).ifPresent(movieDate -> {
                    dto.setDateId(movieDate.getDateId());
                    dto.setMovStart(movieDate.getMovStart());
                    dto.setMovEnd(movieDate.getMovEnd());
                });

                // Fetch all showtimes for this movie and theatre
                List<ShowTimeModel> showTimeModels = showTimeRepo.findAllByMovieIdAndTheatreId(movie.getMovieId(), theatreId);

                // Map showTimeModels to ShowDTO list
                List<ShowDTO> showDTOList = showTimeModels.stream().map(showTime -> {
                    ShowDTO showDTO = new ShowDTO();
                    showDTO.setShowTimeId(showTime.getShowtimeId());
                    showDTO.setShowStart(showTime.getShowStart());
                    return showDTO;
                }).toList();

                dto.setShowTimes(showDTOList);

            } else {
                dto.setMovieId(null);
                dto.setMovieName("No Movie Assigned");
                dto.setShowTimes(new ArrayList<>());
            }

            // Remove this line if you want to stop showing a single showTimeId

            responseList.add(dto);
        }

        return responseList;
    }




    public ResponseEntity<?> addcate(TicketCateChargeModel ticketCateChargeModel, Integer theatreId) {
        try {
            // Check if theatre exists
            if (!theatreRepo.existsById(theatreId)) {
                return new ResponseEntity<>("Theatre not found with ID: " + theatreId, HttpStatus.NOT_FOUND);
            }

            // Set theatreId
            ticketCateChargeModel.setTheatreId(theatreId);

            // Save ticket category charge
            TicketCateChargeModel savedModel = ticketcateChargeRepo.save(ticketCateChargeModel);

            return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add ticket category charge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> deletecate(Integer ticketcatechargeId) {
    Optional<TicketCateChargeModel> optionalTicketCateChargeModel = ticketcateChargeRepo.findById(ticketcatechargeId);
    if (optionalTicketCateChargeModel.isPresent()){
            TicketCateChargeModel ticketCateChargeModel = optionalTicketCateChargeModel.get();
            ticketcateChargeRepo.delete(ticketCateChargeModel);
            return new ResponseEntity<>("Ticket category deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updatecate(Integer ticketcatechargeId,Long ticketcharge) {
        Optional<TicketCateChargeModel> optionalTicketCateChargeModel = ticketcateChargeRepo.findById(ticketcatechargeId);
        if (optionalTicketCateChargeModel.isPresent()){
            TicketCateChargeModel ticketCateChargeModel = optionalTicketCateChargeModel.get();
            ticketCateChargeModel.setTicketcharge(ticketcharge);
            ticketcateChargeRepo.save(ticketCateChargeModel);
            return new ResponseEntity<>("Ticket category updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<TicketCateChargeModel>> getcate() {
        List<TicketCateChargeModel> ticketCateChargeModels = ticketcateChargeRepo.findAll();
        return new ResponseEntity<>(ticketCateChargeModels,HttpStatus.OK);
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

    @Transactional
    public List<TheatreScreenMovDTO> getScreensByTheatreId(Integer theatreId) {
        List<TheatreScreenModel> screens = theatreScreenRepo.findByTheatreId(theatreId);
        List<TheatreScreenMovDTO> responseList = new ArrayList<>();

        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(theatreId);
        String theatrename = optionalTheatreModel.map(TheatreModel::getName).orElse("Unknown Theatre");

        for (TheatreScreenModel screen : screens) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();
            dto.setTheatreId(screen.getTheatreId());
            dto.setName(theatrename);
            dto.setScreenId(screen.getScreenId());
            dto.setScreenName(screen.getScreenName());
            dto.setSeatCapacity(screen.getSeatCapacity());

            // Movie info from relation
            MovieModel movie = screen.getMovie();
            if (movie != null) {
                dto.setMovieId(movie.getMovieId());
                dto.setMovieName(movie.getMovieName());
            } else {
                dto.setMovieId(null);
                dto.setMovieName("No Movie Assigned");
            }

            // Movie Dates
            movieDatesRepo.findByScreenId(screen.getScreenId()).ifPresent(movieDate -> {
                dto.setDateId(movieDate.getDateId());
                dto.setMovStart(movieDate.getMovStart());
                dto.setMovEnd(movieDate.getMovEnd());
            });

            // ** Get all show times for this movie and theatre (return list) **
            List<ShowTimeModel> showTimes = new ArrayList<>();
            if (movie != null) {
                showTimes = showTimeRepo.findAllByMovieIdAndTheatreId(movie.getMovieId(), screen.getTheatreId());
            }

            // Map showTimes to your DTO list
            List<ShowDTO> showTimeDTOs = showTimes.stream()
                    .map(showTime -> {
                        ShowDTO stDto = new ShowDTO();
                        stDto.setShowTimeId(showTime.getShowtimeId());
                        stDto.setShowStart(showTime.getShowStart());
                        return stDto;
                    })
                    .collect(Collectors.toList());

            dto.setShowTimes(showTimeDTOs);

            responseList.add(dto);
        }

        return responseList;
    }


    @Transactional
    public ResponseEntity<?> addshowDates(MovieDatesModel movieDatesModel) {
        LocalDate today = LocalDate.now();
        LocalDate movStartDate = movieDatesModel.getMovStart();
        LocalDate movEndDate = movieDatesModel.getMovEnd();

        // 🔍 Print all incoming data
        System.out.println("Received MovieDatesModel: " + movieDatesModel);

        // ✅ Validation: End date should not be in the past
        if (movEndDate.isBefore(today)) {
            return new ResponseEntity<>("Movie end date cannot be in the past", HttpStatus.BAD_REQUEST);
        }

        // ✅ Check for existing show date
        boolean exists = movieDatesRepo.existsByMovieIdAndScreenIdAndTheatreId(
                movieDatesModel.getMovieId(),
                movieDatesModel.getScreenId(),
                movieDatesModel.getTheatreId()
        );

        if (exists) {
            return new ResponseEntity<>("Show date already exists for this movie on this screen.", HttpStatus.CONFLICT);
        }

        // ✅ Fetch movie and screen
        Optional<TheatreScreenModel> optionalTheatreScreenModel =
                theatreScreenRepo.findByTheatreIdAndScreenId(movieDatesModel.getTheatreId(), movieDatesModel.getScreenId());
        Optional<MovieModel> optionalMovieModel = movieRepo.findByMovieId(movieDatesModel.getMovieId());

        if (optionalTheatreScreenModel.isPresent() && optionalMovieModel.isPresent()) {
            MovieModel movie = optionalMovieModel.get();
            LocalDate releaseDate = movie.getReleaseDate();

            // ✅ Validate movStart is on or within 3 days after release date
            if (movStartDate.isBefore(releaseDate) || movStartDate.isAfter(releaseDate.plusDays(3))) {
                return new ResponseEntity<>(
                        "Movie start date must be on or within 3 days after the release date",
                        HttpStatus.BAD_REQUEST
                );
            }

            // ✅ Save the model
            movieDatesRepo.save(movieDatesModel);
            return new ResponseEntity<>(movieDatesModel, HttpStatus.OK);
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

    public ResponseEntity<String> deletemovie(Integer screenId) {
        Optional<TheatreScreenModel> optionalScreen = theatreScreenRepo.findById(screenId);

        if (optionalScreen.isEmpty()) {
            return new ResponseEntity<>("Screen not found", HttpStatus.NOT_FOUND);
        }

        TheatreScreenModel screen = optionalScreen.get();

        // Remove the movie by setting to null
        screen.setMovie(null);

        theatreScreenRepo.save(screen);

        return new ResponseEntity<>("Movie removed from screen successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> updatemovie(Integer movieId, Integer screenId) {
        try {
            // Check if screen exists
            TheatreScreenModel screen = theatreScreenRepo.findById(screenId).orElse(null);
            if (screen == null) {
                return new ResponseEntity<>("Screen not found", HttpStatus.NOT_FOUND);
            }

            // Check if movie exists
            MovieModel movie = movieRepo.findById(movieId).orElse(null);
            if (movie == null) {
                return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
            }

            // Set new movie to the screen
            screen.setMovie(movie);
            theatreScreenRepo.save(screen);

            return new ResponseEntity<>(screen, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<TicketCateChargeModel>> catebytheatre(Integer theatreId) {
        List<TicketCateChargeModel> ticketCateChargeModels = ticketcateChargeRepo.findByTheatreId(theatreId);
        return new ResponseEntity<>(ticketCateChargeModels,HttpStatus.OK);
    }

    public ResponseEntity<?> addtax(TheatreTaxModel theatreTaxModel, Integer theatreId) {
        try {
            // Check if theatre exists
            if (!theatreRepo.existsById(theatreId)) {
                return new ResponseEntity<>("Theatre not found with ID: " + theatreId, HttpStatus.NOT_FOUND);
            }

            // Set theatreId
            theatreTaxModel.setTheatreId(theatreId);

            // Save ticket category charge
            TheatreTaxModel theatreTaxModel1 = theatreTaxRepo.save(theatreTaxModel);

            return new ResponseEntity<>(theatreTaxModel1, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add ticket category charge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getTheatreTax(Integer theatreId) {
        try {
            Optional<TheatreTaxModel> taxModelOptional = theatreTaxRepo.findByTheatreId(theatreId);

            if (taxModelOptional.isPresent()) {
                return new ResponseEntity<>(taxModelOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No tax data found for the theatre", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving tax data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateTax(TheatreTaxModel theatreTaxModel, Integer theatreId) {
        try {
            // Check if theatre exists
            if (!theatreRepo.existsById(theatreId)) {
                return new ResponseEntity<>("Theatre not found with ID: " + theatreId, HttpStatus.NOT_FOUND);
            }

            // Check if tax record exists for the theatre
            Optional<TheatreTaxModel> existingTaxModel = theatreTaxRepo.findByTheatreId(theatreId);

            if (!existingTaxModel.isPresent()) {
                return new ResponseEntity<>("No tax data found for the theatre", HttpStatus.NOT_FOUND);
            }

            // Update the tax record
            TheatreTaxModel updatedTaxModel = existingTaxModel.get();
            updatedTaxModel.setTaxPercentage(theatreTaxModel.getTaxPercentage());

            // Save the updated tax data
            TheatreTaxModel updatedTax = theatreTaxRepo.save(updatedTaxModel);

            return new ResponseEntity<>(updatedTax, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update the tax", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteTax(Integer theatreId) {
        try {
            // Check if theatre exists
            if (!theatreRepo.existsById(theatreId)) {
                return new ResponseEntity<>("Theatre not found with ID: " + theatreId, HttpStatus.NOT_FOUND);
            }

            // Check if tax record exists for the theatre
            Optional<TheatreTaxModel> existingTaxModel = theatreTaxRepo.findByTheatreId(theatreId);

            if (!existingTaxModel.isPresent()) {
                return new ResponseEntity<>("No tax data found for the theatre", HttpStatus.NOT_FOUND);
            }

            // Delete the tax record
            theatreTaxRepo.delete(existingTaxModel.get());

            return new ResponseEntity<>("Tax data deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete the tax", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Transactional
    public List<TheatreScreenMovDTO> getScreenshow(Integer screenId) {
        List<TheatreScreenModel> screens = theatreScreenRepo.findByScreenId(screenId);
        List<TheatreScreenMovDTO> responseList = new ArrayList<>();

        Optional<TheatreModel> optionalTheatreModel = theatreRepo.findById(screenId);
        String theatrename = optionalTheatreModel.map(TheatreModel::getName).orElse("Unknown Theatre");

        for (TheatreScreenModel screen : screens) {
            TheatreScreenMovDTO dto = new TheatreScreenMovDTO();
            dto.setTheatreId(screen.getTheatreId());
            dto.setName(theatrename);
            dto.setScreenId(screen.getScreenId());
            dto.setScreenName(screen.getScreenName());
            dto.setSeatCapacity(screen.getSeatCapacity());

            // Movie info
            MovieModel movie = screen.getMovie();
            if (movie != null) {
                dto.setMovieId(movie.getMovieId());
                dto.setMovieName(movie.getMovieName());

                // Movie Dates
                movieDatesRepo.findByScreenId(screen.getScreenId()).ifPresent(movieDate -> {
                    dto.setDateId(movieDate.getDateId());
                    dto.setMovStart(movieDate.getMovStart());
                    dto.setMovEnd(movieDate.getMovEnd());
                });

                // Fetch all showtimes for this movie and theatre
                List<ShowTimeModel> showTimeModels = showTimeRepo.findAllByMovieIdAndTheatreId(movie.getMovieId(), screenId);

                // Map showTimeModels to ShowDTO list
                List<ShowDTO> showDTOList = showTimeModels.stream().map(showTime -> {
                    ShowDTO showDTO = new ShowDTO();
                    showDTO.setShowTimeId(showTime.getShowtimeId());
                    showDTO.setShowStart(showTime.getShowStart());
                    return showDTO;
                }).toList();

                dto.setShowTimes(showDTOList);

            } else {
                dto.setMovieId(null);
                dto.setMovieName("No Movie Assigned");
                dto.setShowTimes(new ArrayList<>());
            }

            // Remove this line if you want to stop showing a single showTimeId

            responseList.add(dto);
        }

        return responseList;
    }

//    public List<TheatreScreenMovDTO> getTheatreMovieDetails(Integer movieId) {
//        List<TheatreScreenModel> screens = theatreScreenRepo.findByMovie_MovieId(movieId);
//        List<TheatreScreenMovDTO> dtos = new ArrayList<>();
//
//        for (TheatreScreenModel screen : screens) {
//            TheatreModel theatre = screen.getTheatre();
//            MovieModel movie = screen.getMovie();
//            List<MovieDatesModel> showDates = screen.getShowDates();
//
//            for (MovieDatesModel date : showDates) {
//                List<ShowTimeModel> times = date.getShowTimes();
//
//                for (ShowTimeModel time : times) {
//                    TheatreScreenMovDTO dto = new TheatreScreenMovDTO();
//                    dto.setTheatreId(theatre.getTheatreId());
//                    dto.setName(theatre.getName());
//                    dto.setScreenId(screen.getScreenId());
//                    dto.setScreenName(screen.getScreenName());
//                    dto.setMovieId(movie.getMovieId());
//                    dto.setMovieName(movie.getMovieName());
//                    dto.setSeatCapacity(screen.getSeatCapacity());
//
//                    dto.setDateId(date.getDateId());
//                    dto.setMovStart(date.getMovStart());
//                    dto.setMovEnd(date.getMovEnd());
//
////                    dto.setShowTimeId(time.getShowTimeId());
//                    dto.setShowStart(time.getShowStart());
//
//                    dtos.add(dto);
//                }
//            }
//        }
//
//        return dtos;
//    }


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
