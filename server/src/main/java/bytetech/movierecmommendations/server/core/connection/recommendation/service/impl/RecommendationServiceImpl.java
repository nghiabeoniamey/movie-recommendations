package bytetech.movierecmommendations.server.core.connection.recommendation.service.impl;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationMovieResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationReviewResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationUserResponse;
import bytetech.movierecmommendations.server.core.connection.recommendation.repository.RecommendationMovieRepository;
import bytetech.movierecmommendations.server.core.connection.recommendation.repository.RecommendationReviewRepository;
import bytetech.movierecmommendations.server.core.connection.recommendation.repository.RecommendationUserRepository;
import bytetech.movierecmommendations.server.core.connection.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationMovieRepository movieRepository;

    private final RecommendationUserRepository userRepository;

    private final RecommendationReviewRepository reviewRepository;

    public RecommendationServiceImpl(RecommendationMovieRepository movieRepository, RecommendationUserRepository userRepository, RecommendationReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<RecommendationMovieResponse> getMovies() {
        return movieRepository.getMovies();
    }

    @Override
    public List<RecommendationReviewResponse> getReviews() {
        return reviewRepository.getReviews();
    }

    @Override
    public List<RecommendationUserResponse> getUsers() {
        return userRepository.getUsers();
    }
}
