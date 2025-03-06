package bytetech.movierecmommendations.server.core.connection.recommendation.service.impl;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationMovieResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationReviewResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationUserResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<RecommendationMovieResponse> getMovies() {
        return List.of();
    }

    @Override
    public List<RecommendationReviewResponse> getReviews() {
        return List.of();
    }

    @Override
    public List<RecommendationUserResponse> getUsers() {
        return List.of();
    }
}
