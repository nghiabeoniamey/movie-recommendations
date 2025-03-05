package bytetech.movierecmommendations.server.core.user.review.controller;

import bytetech.movierecmommendations.server.core.user.review.model.request.UserFindReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserModifyReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.service.UserReviewService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MappingConstant.API_USER_REVIEW)
@RequiredArgsConstructor
@RestController
public class UserReviewController {

    private final UserReviewService reviewService;

    @GetMapping()
    public ResponseEntity<?> getReviews(@Valid final UserFindReviewRequest request) {
        return Helper.createResponseEntity(reviewService.getReviews(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable String id) {
        return Helper.createResponseEntity(reviewService.getReviewById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createReview(@Valid @RequestBody final UserReviewRequest request) {
        return Helper.createResponseEntity(reviewService.createReview(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable String id, @Valid @RequestBody final UserModifyReviewRequest request) {
        return Helper.createResponseEntity(reviewService.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id) {
        return Helper.createResponseEntity(reviewService.deleteReviewById(id));
    }

}
