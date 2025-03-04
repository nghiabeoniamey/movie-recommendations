package bytetech.movierecmommendations.server.core.client.movei.service;

import bytetech.movierecmommendations.server.entities.main.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> searchMovie(String keyword);
    List<Movie> filterMovies(String sortBy);



}
