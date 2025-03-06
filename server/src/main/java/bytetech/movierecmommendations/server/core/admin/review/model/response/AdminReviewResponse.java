package bytetech.movierecmommendations.server.core.admin.review.model.response;

import bytetech.movierecmommendations.server.core.common.base.BaseResponse;

public interface AdminReviewResponse extends BaseResponse {

    String getComment();

    String getRating();

    String getUserId();

    String getMovieId();

    Long getCreatedDate();

    Long getLastModifiedDate();

}
