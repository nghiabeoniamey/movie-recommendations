package bytetech.movierecmommendations.server.core.admin.review.service;

import bytetech.movierecmommendations.server.core.admin.review.model.request.AdminFindReviewRequest;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;

public interface AdminReviewService {

    ResponseObject<?> getReviews(AdminFindReviewRequest request);

    ResponseObject<?> getReviewById(String id);

    ResponseObject<?> deleteReviewById(String id);

}
