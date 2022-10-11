package pe.ineapp.ineappmovieservice.Movie.application.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ineapp.ineappmovieservice.Movie.domain.entity.Movie;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.repository.MovieRepository;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.request.MovieRequest;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.BasicResponse;
import pe.ineapp.ineappmovieservice.Movie.infraestructure.response.MovieResponse;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public BasicResponse addMovie(MovieRequest request) {

        try{
            movieRepository.save(this.buildMovieFromRequest(request));
            return BasicResponse.whenSuccess();
        }
        catch(Exception e){
            log.error(e.getMessage());
            return BasicResponse.whenError(e.getMessage());
        }
    }

    @Override
    public MovieResponse getAll(){
        List<Movie> movieList = movieRepository.findAll();

        if(movieList.isEmpty()) {
            return MovieResponse.builder()
                    .movieList(null)
                    .basicResponse(BasicResponse.whenNoDataFound("User"))
                    .build();
        }

        return MovieResponse.builder()
                .movieList(movieList)
                .basicResponse(BasicResponse.whenSuccess())
                .build();
    }

    @Override
    public MovieResponse getByTitle(String title)
    {
        Movie movie = movieRepository.findByTitle(title);

        if (movie!= null){
            return MovieResponse.builder()
                    .movieList(List.of(movie))
                    .basicResponse(BasicResponse.whenSuccess())
                    .build();
        }else{
           return MovieResponse.builder()
                   .movieList(null)
                   .basicResponse(BasicResponse.whenNoDataFound("User"))
                   .build();
        }

    }


    public BasicResponse deleteMovie(String title) {
       try{
           Movie movie = movieRepository.findByTitle(title);

           if (movie == null){
               return BasicResponse.whenNoDataFound("La pelicula con titulo " + title);
           }else{
               movieRepository.delete(movie);
               return BasicResponse.whenSuccess();
           }
       }catch (Exception e)
       {
           return BasicResponse.whenError(e.getMessage());
       }
    }

    @Transactional
    @Override
    public BasicResponse updateMovie(MovieRequest request, String title){
        try{
            Movie movie = movieRepository.findByTitle(title);

            if (movie == null){
                return BasicResponse.whenNoDataFound("La pelicula de nombre " + title);
            }else{

               movie.setTitle(request.getTitle()!=null && !request.getTitle().isBlank() ? request.getTitle() : movie.getTitle());
               movie.setGenre(request.getGenre()!=null && !request.getGenre().isBlank() ? request.getGenre() : movie.getGenre());
               movie.setYear(request.getYear()!=null && !request.getYear().isBlank() ? request.getYear() : movie.getYear());
               movie.setDirector(request.getDirector()!=null && !request.getDirector().isBlank() ? request.getDirector() : movie.getDirector());
               movie.setRating(request.getRating()!=null && !request.getRating().isBlank() ? request.getRating() : movie.getRating());

               return BasicResponse.whenSuccess();
            }
        }
        catch(Exception e){
            return BasicResponse.whenError(e.getMessage());
        }

    }


    public Movie buildMovieFromRequest(MovieRequest request){
        return Movie.builder()
                .title(request.getTitle())
                .year(request.getYear())
                .genre(request.getGenre())
                .director(request.getDirector())
                .rating(request.getRating()).build();
    }
}
