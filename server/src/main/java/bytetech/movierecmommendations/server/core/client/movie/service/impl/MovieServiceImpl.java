package bytetech.movierecmommendations.server.core.client.movie.service.impl;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminModifyMovieResponse;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;
import bytetech.movierecmommendations.server.core.client.movie.model.request.MovieFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.model.response.UserModifyMovieResponse;
import bytetech.movierecmommendations.server.core.client.movie.repository.ClientMovieRepository;
import bytetech.movierecmommendations.server.core.client.movie.service.MovieService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResponseObject<?> getMovieById(String id) {
        try {
            UserModifyMovieResponse movieResponse = clientMovieRepository.findMovieById(id);

            if (movieResponse != null) {
                List<AdminMovieCategoryResponse> categories = clientMovieRepository.findCategoryByMoviesId(id);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("id", movieResponse.getId());
                responseMap.put("title", movieResponse.getTitle());
                responseMap.put("description", movieResponse.getDescription());
                responseMap.put("pictureURL", movieResponse.getPictureURL());
                responseMap.put("movie", movieResponse.getmovie());
                responseMap.put("author", movieResponse.getAuthor());
                responseMap.put("actor", movieResponse.getActor());
                responseMap.put("year", movieResponse.getYear());
                responseMap.put("createdDate", movieResponse.getCreatedDate());
                responseMap.put("lastModifiedDate", movieResponse.getLastModifiedDate());
                responseMap.put("categories", categories != null ? categories : Collections.emptyList());

                return new ResponseObject<>(
                        responseMap,
                        HttpStatus.OK,
                        Message.Success.GET_SUCCESS
                );
            }

            return new ResponseObject<>(
                    null,
                    HttpStatus.NOT_FOUND,
                    Message.Error.GET_ERROR
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}




