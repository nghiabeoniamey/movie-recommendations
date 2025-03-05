package bytetech.movierecmommendations.server.core.client.movie.service.impl;

import bytetech.movierecmommendations.server.core.client.movie.repository.ClientMovieRepository;
import bytetech.movierecmommendations.server.core.client.movie.service.MovieService;
import bytetech.movierecmommendations.server.entities.main.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ClientMovieRepository clientMovieRepository;

    public List<Movie> searchMovie(String keyword) {
        return clientMovieRepository.searchMovies(keyword);
    }

    @Override
    public List<Movie> filterMovies(String sortBy) {
        return clientMovieRepository.filterMovies(sortBy);
    }

}
