package pe.ineapp.ineappmovieservice.Movie.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ineapp.ineappmovieservice.Movie.domain.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    public Movie findByTitle(String title);

}
