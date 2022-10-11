package pe.ineapp.ineappmovieservice.Movie.infraestructure.response;

import lombok.Builder;
import lombok.Data;
import pe.ineapp.ineappmovieservice.Movie.domain.entity.Movie;

import java.util.List;

@Data
@Builder
public class MovieResponse {

    private List<Movie> movieList;
    private BasicResponse basicResponse;

}
