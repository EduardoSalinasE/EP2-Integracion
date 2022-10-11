package pe.ineapp.ineappmovieservice.Movie.infraestructure.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieRequest {

    private String title;
    private String year;
    private String genre;
    private String director;
    private String raiting;
}
