package bytetech.movierecmommendations.server.core.connection.recommendation.repository;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationReviewResponse;
import bytetech.movierecmommendations.server.repository.ReviewerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationReviewRepository extends ReviewerRepository {

    @Query(value = """
            SELECT
                r.user_id AS userId,
                r.movie_id AS movieId,
                r.rating AS rating,
                r.comment AS comment
            FROM reviewer r
            WHERE r.deleted = false
            """, nativeQuery = true)
    List<RecommendationReviewResponse> getReviews();

}
