package bytetech.movierecmommendations.server.core.connection.python.repository;

import bytetech.movierecmommendations.server.core.connection.python.model.response.PythonMovieResponse;
import bytetech.movierecmommendations.server.repository.MovieRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PythonMovieRepository extends MovieRepository {

    @Query(value = """
            SELECT
                m.id AS id,
                m.title AS title,
                m.description AS description,
                m.picture AS pictureURL,
                m.movies AS movieURL,
                m.author AS author,
                m.actor AS actor,
                m.year AS year,
                m.created_date AS createdDate,
                m.last_modified_date AS lastModifiedDate,
                COALESCE(ROUND(AVG(r.rating * 2), 1), 0) AS rating
            FROM
                movie m
            LEFT JOIN movie_category mc on m.id = mc.movie_id
            LEFT JOIN category c on c.id = mc.category_id
            LEFT JOIN reviewer r on r.movie_id = m.id AND r.deleted = false
            WHERE m.id IN (:movieIds)
            GROUP BY m.id,
                     m.title,
                     m.description,
                     m.picture,
                     m.movies,
                     m.author,
                     m.actor,
                     m.year,
                     m.created_date,
                     m.last_modified_date
            """, nativeQuery = true)
    List<PythonMovieResponse> getMoviesByMovieIds(List<String> movieIds);

    @Query(value = """
            SELECT
                m.id AS id,
                m.title AS title,
                m.description AS description,
                m.picture AS pictureURL,
                m.movies AS movieURL,
                m.author AS author,
                m.actor AS actor,
                m.year AS year,
                m.created_date AS createdDate,
                m.last_modified_date AS lastModifiedDate,
                COALESCE(ROUND(AVG(r.rating * 2), 1), 0) AS rating
            FROM
                movie m
            LEFT JOIN movie_category mc on m.id = mc.movie_id
            LEFT JOIN category c on c.id = mc.category_id
            LEFT JOIN reviewer r on r.movie_id = m.id AND r.deleted = false
            JOIN watch_history wh on wh.movie_id = m.id AND wh.user_id = :userId
            GROUP BY m.id,
                     m.title,
                     m.description,
                     m.picture,
                     m.movies,
                     m.author,
                     m.actor,
                     m.year,
                     m.created_date,
                     m.last_modified_date
            """, nativeQuery = true)
    List<PythonMovieResponse> getMoviesHistoryByUserId(String userId);

    @Query(value = """
            SELECT
                m.id AS id,
                m.title AS title,
                m.description AS description,
                m.picture AS pictureURL,
                m.movies AS movieURL,
                m.author AS author,
                m.actor AS actor,
                m.year AS year,
                m.created_date AS createdDate,
                m.last_modified_date AS lastModifiedDate,
                COALESCE(ROUND(AVG(r.rating * 2), 1), 0) AS rating
            FROM
                movie m
            LEFT JOIN movie_category mc on m.id = mc.movie_id
            LEFT JOIN category c on c.id = mc.category_id
            JOIN reviewer r on r.movie_id = m.id AND r.deleted = false AND r.user_id = :userId
            GROUP BY m.id,
                     m.title,
                     m.description,
                     m.picture,
                     m.movies,
                     m.author,
                     m.actor,
                     m.year,
                     m.created_date,
                     m.last_modified_date
            """, nativeQuery = true)
    List<PythonMovieResponse> getMoviesReviewByUserId(String userId);

}
