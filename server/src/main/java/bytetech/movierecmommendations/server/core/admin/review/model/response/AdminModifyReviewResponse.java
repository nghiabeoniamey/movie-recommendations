package bytetech.movierecmommendations.server.core.admin.review.model.response;

public interface AdminModifyReviewResponse {

    String getId();

    String getComment();

    String getRating();

    String getUserId();

    Long getCreatedDate();

    Long getLastModifiedDate();

}
