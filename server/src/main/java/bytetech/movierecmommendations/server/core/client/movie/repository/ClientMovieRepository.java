package bytetech.movierecmommendations.server.core.client.movie.repository;

import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.repository.MovieRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientMovieRepository extends MovieRepository {

    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCategory mc ON mc.movie = m " +
            "JOIN mc.category c " +
            "WHERE " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.actor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(m.year AS string) LIKE %:keyword% OR " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Movie> searchMovies(@Param("keyword") String keyword);

    @Query(value = """
        SELECT m.* FROM movie m
        LEFT JOIN reviewer r ON m.id = r.movie_id
        LEFT JOIN watch_history wh ON m.id = wh.movie_id
        GROUP BY m.id
        ORDER BY
            CASE
                WHEN :sortBy = 'highestRated' THEN AVG(r.rating)
            END DESC,
            CASE
                WHEN :sortBy = 'newest' THEN m.year
            END DESC,
            CASE
                WHEN :sortBy = 'mostPopular' THEN COUNT(wh.movie_id)
            END DESC
        """, nativeQuery = true)
    List<Movie> filterMovies(@Param("sortBy") String sortBy);
}
