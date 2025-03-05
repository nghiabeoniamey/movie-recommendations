package bytetech.movierecmommendations.server.core.user.review.model.response;

import bytetech.movierecmommendations.server.core.common.base.BaseResponse;

public interface UserReviewResponse extends BaseResponse {

    String getComment();

    String getRating();

    String getUserId();

    Long getCreatedDate();

    Long getLastModifiedDate();

}
