package pe.ineapp.ineappmovieservice.Movie.application.service;

import pe.ineapp.ineappmovieservice.Movie.domain.entity.Movie;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.request.MovieRequest;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.BasicResponse;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.MovieResponse;

import javax.transaction.Transactional;

public interface MovieService {

    public BasicResponse addMovie(MovieRequest request);

    public MovieResponse getAll();

    public MovieResponse getByTitle(String title);

    public BasicResponse deleteMovie(String title);

    @Transactional
    BasicResponse updateMovie(MovieRequest request, String title);
}
