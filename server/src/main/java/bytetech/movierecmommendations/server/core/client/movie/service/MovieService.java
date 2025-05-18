package bytetech.movierecmommendations.server.core.client.movie.service;

import bytetech.movierecmommendations.server.core.client.movie.model.request.MovieFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.model.request.WatchRequest;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {

    List<Movie> searchMovie(String keyword);

    List<Movie> filterMovies(String sortBy);

    ResponseObject<Page<Movie>> getAll(MovieFilterRequest filterRequest, int page, int size);

    ResponseObject<?> getMovieById(String id);

    ResponseObject<?> viewMovie(WatchRequest req);
}
