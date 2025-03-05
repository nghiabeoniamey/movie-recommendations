package bytetech.movierecmommendations.server.core.user.review.service;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserModifyReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserFindReviewRequest;
import jakarta.validation.Valid;

public interface UserReviewService {

    ResponseObject<?> getReviews(UserFindReviewRequest request);

    ResponseObject<?> getReviewById(String id);

    ResponseObject<?> createReview(@Valid UserReviewRequest request);

    ResponseObject<?> updateReview(String id, @Valid UserModifyReviewRequest request);

    ResponseObject<?> deleteReviewById(String id);

}
