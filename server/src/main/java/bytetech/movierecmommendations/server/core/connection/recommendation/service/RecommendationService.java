package bytetech.movierecmommendations.server.core.connection.recommendation.service;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationMovieResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationReviewResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationUserResponse;

import java.util.List;

public interface RecommendationService {

    List<RecommendationMovieResponse> getMovies();

    List<RecommendationReviewResponse> getReviews();

    List<RecommendationUserResponse> getUsers();

}
