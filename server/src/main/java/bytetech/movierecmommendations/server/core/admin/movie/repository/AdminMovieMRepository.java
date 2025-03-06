package bytetech.movierecmommendations.server.core.admin.movie.repository;

import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminFindMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminModifyMovieResponse;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieResponse;
import bytetech.movierecmommendations.server.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminMovieMRepository extends MovieRepository {

    @Query(value = """
            SELECT
                ROW_NUMBER() OVER(ORDER BY m.created_date DESC) AS catalog,
                m.id AS id,
                m.title AS title,
                m.description AS description,
                m.picture AS pictureURL,
                m.movies AS movieURL,
                m.author AS author,
                m.actor AS actor,
                m.year AS year,
                m.created_date AS createdDate,
                m.last_modified_date AS lastModifiedDate
            FROM
                movie m
            LEFT JOIN movie_category mc on m.id = mc.movie_id
            LEFT JOIN category c on c.id = mc.category_id
            WHERE
                (:#{#req.keyword} IS NULL OR
                m.title LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                m.description LIKE CONCAT('%', :#{#req.keyword}, '%')) OR
                m.actor LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                m.author LIKE CONCAT('%', :#{#req.keyword}, '%')
            AND (:#{#req.startDate} IS NULL OR :#{#req.endDate} IS NULL OR m.year BETWEEN :#{#req.startDate} AND :#{#req.endDate})
            AND c.id IN (:#{#req.categoryId})
            """, countQuery = """
            SELECT
                COUNT(m.id) FROM movie m
            LEFT JOIN movie_category mc on m.id = mc.movie_id
            LEFT JOIN category c on c.id = mc.category_id
            WHERE
                (:#{#req.keyword} IS NULL OR
                m.title LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                m.description LIKE CONCAT('%', :#{#req.keyword}, '%')) OR
                m.actor LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                m.author LIKE CONCAT('%', :#{#req.keyword}, '%')
            AND (:#{#req.startDate} IS NULL OR :#{#req.endDate} IS NULL OR m.year BETWEEN :#{#req.startDate} AND :#{#req.endDate})
            AND c.id IN (:#{#req.categoryId})
            """, nativeQuery = true)
    Page<AdminMovieResponse> getMovies(Pageable pageable, @Param("req") AdminFindMovieRequest req);

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
                m.last_modified_date AS lastModifiedDate
            FROM movie m
            WHERE m.id = :id
            """, nativeQuery = true)
    AdminModifyMovieResponse findMovieById(String id);

    @Query(value = """
            SELECT
                c.id AS id,
                c.name AS name,
                c.description AS description
            FROM category c
            JOIN movie_category mc on c.id = mc.category_id AND mc.movie_id = :movieId
            WHERE c.deleted = false
            """, nativeQuery = true)
    List<AdminMovieCategoryResponse> findCategoryByMoviesId(String movieId);

    @Query(value = """
            SELECT
                c.id AS id,
                c.name AS name,
                c.description AS description,
                mc.movie_id AS movieId
            FROM category c
            JOIN movie_category mc on c.id = mc.category_id AND mc.movie_id IN :movieId
            WHERE c.deleted = false
            """, nativeQuery = true)
    List<AdminMovieCategoryResponse> findCategoryByMoviesIds(List<String> movieId);
}
