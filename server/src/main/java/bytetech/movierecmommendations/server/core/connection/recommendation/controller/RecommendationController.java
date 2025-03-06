package bytetech.movierecmommendations.server.core.connection.recommendation.controller;

import bytetech.movierecmommendations.server.core.connection.recommendation.service.RecommendationService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MappingConstant.API_CONNECTION_RECOMMENDATION)
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getMovies() {
        return ResponseEntity.ok(recommendationService.getMovies());
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviews() {
        return ResponseEntity.ok(recommendationService.getReviews());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(recommendationService.getUsers());
    }

}
