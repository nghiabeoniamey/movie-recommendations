package bytetech.movierecmommendations.server.core.admin.movie.model.response;

import java.util.List;

public interface AdminModifyMovieResponse {

    String getId();

    String getTitle();

    String getDescription();

    String getPictureURL();

    String getMoviesURL();

    String getAuthor();

    String getActor();

    Integer getYear();

    List<AdminMovieCategoryResponse> getCategories();

    Long getCreatedDate();

    Long getLastModifiedDate();

}
