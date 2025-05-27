package com.MovieTicketBooking.MovieTicketBooking.Admin;


import com.MovieTicketBooking.MovieTicketBooking.Movie.MovieModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieGenre.MovieGenreModel;
import com.MovieTicketBooking.MovieTicketBooking.MovieLang.MovieLangModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admindetails")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    //admin regtn
    @PostMapping(path = "/addadmin")
    public ResponseEntity<?> addadmin(@RequestBody AdminModel adminModel){
        try{
            return adminService.addadmin(adminModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //admin login
    @PostMapping("/adminlogin")
    public ResponseEntity<?> login(@RequestBody AdminLoginDto adminLoginDto) {
        try {
            boolean isAuthenticated = adminService.adminLogin(adminLoginDto);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, please try again.");
        }
    }


    //Add movie by admin
    @PostMapping(path = "/addMovie")
    public ResponseEntity<?> addMovie(@RequestPart MovieModel movieModel,
                                      @RequestPart MultipartFile movieposter){
        try{
            return adminService.addmovies(movieModel,movieposter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Delete Movie by admin
    @DeleteMapping(path = "/deletemovie")
    public ResponseEntity<?> deleteMovie(@RequestParam Integer movieId){
        try{
            return adminService.deletemovies(movieId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update movie name and description
    @PutMapping(path = "/updatemovieNameDes")
    public ResponseEntity<?> updatemovie(@RequestParam Integer movieId,@RequestParam String movieName,@RequestParam String description){
        try{
            return adminService.updateMovieNameAndDes(movieId,movieName,description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update movie language and genre
    @PutMapping(path = "/updatemovielangenre")
    public ResponseEntity<?> updatemovie(@RequestParam Integer movieId,@RequestParam Integer language,@RequestParam Integer genre){
        try{
            return adminService.updateMovieLangAndGenre(movieId,language,genre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.OK);
    }

    //Reset password Admin
    @PutMapping(path = "/resetPassword")
    public ResponseEntity<?> updateAdmin(@RequestParam String email,@RequestParam String password){
        try{
            return adminService.updateAdmin(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.OK);
    }


    //add movie languages
    @PostMapping(path = "/addlang")
    public ResponseEntity<?> addlang(@RequestBody MovieLangModel movieLangModel) {
        return adminService.addlang(movieLangModel);
    }

    //delete movie language
    @DeleteMapping(path = "/deletelang")
    public ResponseEntity<?> deletelang(@RequestParam Integer langId) {
        try {
            return adminService.deletelang(langId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Update movie language
    @PutMapping(path = "/updatelang")
    public ResponseEntity<?> updatelang(@RequestParam Integer langId, @RequestParam String langName) {
        try {
            return adminService.updatelang(langId, langName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all movie languages
    @GetMapping(path = "/getallang")
    public ResponseEntity<List<MovieLangModel>> allang() {
        return adminService.allang();
    }

    //add movie genre
    @PostMapping(path = "/addgenre")
    public ResponseEntity<?> addgenre(@RequestBody MovieGenreModel movieGenreModel) {
        try {
            return adminService.addgenre(movieGenreModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //delete movie genre
    @DeleteMapping(path = "/deletegenre")
    public ResponseEntity<?> deletegenre(@RequestParam Integer genreId) {
        try {
            return adminService.deletegenre(genreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //update movie genre
    @PutMapping(path = "/updategenre")
    public ResponseEntity<?> updategenre(@RequestParam Integer genreId, @RequestParam String genreName) {
        try {
            return adminService.updategenre(genreId, genreName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all genres
    @GetMapping(path = "/getgenre")
    public ResponseEntity<List<MovieGenreModel>> getgenre() {
        return adminService.getgenre();
    }

}
