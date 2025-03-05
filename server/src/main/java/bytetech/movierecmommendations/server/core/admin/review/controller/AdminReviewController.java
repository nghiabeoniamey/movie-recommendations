package bytetech.movierecmommendations.server.core.admin.review.controller;

import bytetech.movierecmommendations.server.core.admin.review.model.request.AdminFindReviewRequest;
import bytetech.movierecmommendations.server.core.admin.review.service.AdminReviewService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MappingConstant.API_ADMIN_REVIEW)
@RequiredArgsConstructor
@RestController
public class AdminReviewController {

    private final AdminReviewService reviewService;

    @GetMapping()
    public ResponseEntity<?> getReviews(@Valid final AdminFindReviewRequest request) {
        return Helper.createResponseEntity(reviewService.getReviews(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable String id) {
        return Helper.createResponseEntity(reviewService.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable String id) {
        return Helper.createResponseEntity(reviewService.deleteReviewById(id));
    }

}
