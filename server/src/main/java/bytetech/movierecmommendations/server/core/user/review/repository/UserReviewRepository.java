package bytetech.movierecmommendations.server.core.user.review.repository;

import bytetech.movierecmommendations.server.core.user.review.model.request.UserFindReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.response.UserModifyReviewResponse;
import bytetech.movierecmommendations.server.core.user.review.model.response.UserReviewResponse;
import bytetech.movierecmommendations.server.repository.ReviewerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserReviewRepository extends ReviewerRepository {

    @Query(value = """
            SELECT
                ROW_NUMBER() OVER(ORDER BY r.created_date DESC) AS catalog,
                r.id AS id,
                r.comment AS comment,
                r.rating AS rating,
                r.user_id AS userId,
                r.created_date AS createdDate,
                r.last_modified_date AS lastModifiedDate
            FROM
                reviewer r
            WHERE
                (:#{#req.keyword} IS NULL OR
                r.comment LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                r.rating LIKE CONCAT('%', :#{#req.keyword}, '%'))
            AND (:#{#req.startDate} IS NULL OR :#{#req.endDate} IS NULL OR r.created_date BETWEEN :#{#req.startDate} AND :#{#req.endDate})
            AND (:#{#req.movieId} IS NULL OR r.id = :#{#req.movieId})
            AND (:#{#req.rating} IS NULL OR r.rating = :#{#req.rating})
            AND r.deleted = false
            """, countQuery = """
            SELECT
                COUNT(r.id) FROM reviewer r
            WHERE
                (:#{#req.keyword} IS NULL OR
                r.comment LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                r.rating LIKE CONCAT('%', :#{#req.keyword}, '%'))
            AND (:#{#req.startDate} IS NULL OR :#{#req.endDate} IS NULL OR r.created_date BETWEEN :#{#req.startDate} AND :#{#req.endDate})
            AND (:#{#req.movieId} IS NULL OR r.id = :#{#req.movieId})
            AND (:#{#req.rating} IS NULL OR r.rating = :#{#req.rating})
            AND r.deleted = false
            """, nativeQuery = true)
    Page<UserReviewResponse> getReviews(Pageable pageable, @Param("req") UserFindReviewRequest req);

    @Query(value = """
            SELECT
                r.id AS id,
                r.comment AS comment,
                r.rating AS rating,
                r.user_id AS userId,
                r.created_date AS createdDate,
                r.last_modified_date AS lastModifiedDate
            FROM reviewer r
            WHERE r.id = :id
            """, nativeQuery = true)
    UserModifyReviewResponse findUserById(String id);

}
