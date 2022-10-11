package pe.ineapp.ineappmovieservice.Movie.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.ineapp.ineappmovieservice.Movie.domain.entity.Movie;
import pe.ineapp.ineappmovieservice.Movie.application.service.MovieService;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.request.MovieRequest;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.BasicResponse;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.MovieResponse;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;



    @GetMapping
    @RequestMapping("/getall")
    public MovieResponse getAll(){

        return movieService.getAll();

    }

    @GetMapping
    @RequestMapping("/getbytitle")
    public MovieResponse getByTitle(@RequestParam String title)
    {
        return movieService.getByTitle(title);
    }


    @PostMapping
    @RequestMapping("/addmovie")
    public ResponseEntity<BasicResponse> addMovie(@RequestBody MovieRequest request){
        BasicResponse response = movieService.addMovie(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping
    @RequestMapping("/updatemovie")
    public ResponseEntity<BasicResponse> updateMovie(@RequestBody MovieRequest request, @RequestParam String title){
        BasicResponse response = movieService.updateMovie(request, (title));
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping
    @RequestMapping("/deletemovie")
    public ResponseEntity<BasicResponse> deleteMovie(@RequestParam String title){
        BasicResponse response = movieService.deleteMovie(title);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
