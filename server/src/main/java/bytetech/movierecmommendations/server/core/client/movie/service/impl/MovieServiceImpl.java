package bytetech.movierecmommendations.server.core.client.movie.service.impl;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.model.request.MovieFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.repository.ClientMovieRepository;
import bytetech.movierecmommendations.server.core.client.movie.service.MovieService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.entities.main.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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


    @Override
    public ResponseObject<Page<Movie>> getAll(MovieFilterRequest filterRequest, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Movie> movies = clientMovieRepository.findAllByFilter(
                filterRequest.getMovieId(),
                pageable
        );
        return new ResponseObject<>(movies, HttpStatus.OK, "Lấy danh sách phim thành công");
    }

}




