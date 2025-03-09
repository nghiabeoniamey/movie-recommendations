package bytetech.movierecmommendations.server.core.client.movie.model.response;

import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;

import java.util.List;

public interface UserModifyMovieResponse {

    String getId();

    String getTitle();

    String getDescription();

    String getPictureURL();

    String getmovie();

    String getAuthor();

    String getActor();

    Integer getYear();

    List<AdminMovieCategoryResponse> getCategories();

    Long getCreatedDate();

    Long getLastModifiedDate();


}
