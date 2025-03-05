package bytetech.movierecmommendations.server.core.user.review.model.response;

public interface UserModifyReviewResponse {

    String getComment();

    String getRating();

    String getUserId();

    Long getCreatedDate();

    Long getLastModifiedDate();

}
