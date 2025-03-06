package bytetech.movierecmommendations.server.core.connection.recommendation.model.response;

import java.util.List;

public interface RecommendationMovieResponse {

    String getId();

    List<String> getGenres();

    Integer getReleaseYear();

}
