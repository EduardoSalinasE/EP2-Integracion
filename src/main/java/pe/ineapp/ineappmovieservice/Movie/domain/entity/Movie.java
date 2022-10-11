package pe.ineapp.ineappmovieservice.Movie.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity(name = "Movie")
@Table(
        name="Movie",
        uniqueConstraints = {
                @UniqueConstraint(name = "movie_Title_unique", columnNames = "Title"),
        }

)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {

    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Id
    Long id;
    String title;
    String year;
    String genre;
    String director;
    String raiting;


}
