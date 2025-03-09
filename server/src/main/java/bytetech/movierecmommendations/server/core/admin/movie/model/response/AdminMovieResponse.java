package bytetech.movierecmommendations.server.core.admin.movie.model.response;

import bytetech.movierecmommendations.server.core.common.base.BaseResponse;

import java.util.List;

public interface AdminMovieResponse extends BaseResponse {

    String getTitle();

    String getDescription();

    String getPictureURL();

    String getMovieURL();

    String getAuthor();

    String getActor();

    Integer getYear();

    List<AdminMovieCategoryResponse> getCategories();

    Long getCreatedDate();

    Long getLastModifiedDate();

    Double getRating();

}
