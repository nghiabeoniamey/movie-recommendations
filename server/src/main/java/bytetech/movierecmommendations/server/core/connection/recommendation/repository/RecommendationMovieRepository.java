package bytetech.movierecmommendations.server.core.connection.recommendation.repository;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationMovieResponse;
import bytetech.movierecmommendations.server.repository.MovieRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationMovieRepository extends MovieRepository {

    @Query(value = """
            SELECT
                  m.id AS id,
                  m.year AS releaseYear,
                  GROUP_CONCAT(c.id ORDER BY c.id SEPARATOR ', ') AS genres
            FROM movie m
            JOIN movie_category mc ON m.id = mc.movie_id
            JOIN category c ON c.id = mc.category_id
            WHERE m.deleted = false
            GROUP BY m.id, m.year
            """, nativeQuery = true)
    List<RecommendationMovieResponse> getMovies();

}
