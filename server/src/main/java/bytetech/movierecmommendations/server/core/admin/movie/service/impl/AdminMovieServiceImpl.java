package bytetech.movierecmommendations.server.core.admin.movie.service.impl;

import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminFindMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminModifyMovieResponse;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;
import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieResponse;
import bytetech.movierecmommendations.server.core.admin.movie.repository.AdminMovieCRepository;
import bytetech.movierecmommendations.server.core.admin.movie.repository.AdminMovieMCRepository;
import bytetech.movierecmommendations.server.core.admin.movie.repository.AdminMovieMRepository;
import bytetech.movierecmommendations.server.core.admin.movie.service.AdminMovieService;
import bytetech.movierecmommendations.server.core.common.base.PageableObject;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.entities.main.MovieCategory;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMovieServiceImpl implements AdminMovieService {

    private final AdminMovieMRepository movieRepository;

    private final AdminMovieCRepository categoryRepository;

    private final AdminMovieMCRepository movieCategoryRepository;

    @Override
    public ResponseObject<?> getMovie(AdminFindMovieRequest request) {
        try {
            Pageable pageable = Helper.createPageable(request);
            Page<AdminMovieResponse> movieResponsePage = movieRepository.getMovies(pageable, request);
            if (movieResponsePage.hasContent()) {
                List<AdminMovieResponse> movies = movieResponsePage.getContent();

                List<String> movieIds = movieResponsePage.getContent().stream()
                        .map(AdminMovieResponse::getId)
                        .toList();

                List<AdminMovieCategoryResponse> categories = movieRepository.findCategoryByMoviesIds(movieIds);

                Map<String, List<AdminMovieCategoryResponse>> categoryMap = categories.stream()
                        .collect(Collectors.groupingBy(
                                AdminMovieCategoryResponse::getMovieId
                        ));

                List<Map<String, Object>> movieList = movies.stream().map(movie -> {
                    Map<String, Object> movieMap = new HashMap<>();
                    movieMap.put("id", movie.getId());
                    movieMap.put("title", movie.getTitle());
                    movieMap.put("description", movie.getDescription());
                    movieMap.put("pictureURL", movie.getPictureURL());
                    movieMap.put("moviesURL", movie.getMoviesURL());
                    movieMap.put("author", movie.getAuthor());
                    movieMap.put("actor", movie.getActor());
                    movieMap.put("year", movie.getYear());
                    movieMap.put("rating", movie.getRating());
                    movieMap.put("createdDate", movie.getCreatedDate());
                    movieMap.put("lastModifiedDate", movie.getLastModifiedDate());
                    movieMap.put("categories", categoryMap.getOrDefault(movie.getId(), new ArrayList<>()));

                    return movieMap;
                }).distinct().toList();

                return new ResponseObject<>(
                        PageableObject.of(
                                movieList,
                                movieResponsePage.getTotalPages(),
                                movieResponsePage.getNumber(),
                                movieResponsePage.getTotalElements()
                        ),
                        HttpStatus.OK,
                        Message.Success.GET_SUCCESS);
            }

            return new ResponseObject<>(
                    PageableObject.of(movieResponsePage),
                    HttpStatus.OK,
                    Message.Success.GET_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseObject<?> getMovieById(String id) {
        try {
            AdminModifyMovieResponse movieResponse = movieRepository.findMovieById(id);

            if (movieResponse != null) {
                List<AdminMovieCategoryResponse> categories = movieRepository.findCategoryByMoviesId(id);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("id", movieResponse.getId());
                responseMap.put("title", movieResponse.getTitle());
                responseMap.put("description", movieResponse.getDescription());
                responseMap.put("pictureURL", movieResponse.getPictureURL());
                responseMap.put("moviesURL", movieResponse.getMoviesURL());
                responseMap.put("author", movieResponse.getAuthor());
                responseMap.put("actor", movieResponse.getActor());
                responseMap.put("year", movieResponse.getYear());
                responseMap.put("rating", movieResponse.getRating());
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

    @Override
    public ResponseObject<?> createMovie(AdminMovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPicture(request.getPictureURL());
        movie.setMovies(request.getMoviesURL());
        movie.setAuthor(request.getAuthor());
        movie.setActor(request.getActor());
        movie.setYear(request.getYear());
        Movie newMovie = movieRepository.save(movie);
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if (!categories.isEmpty()) {
            List<MovieCategory> movieCategories = new ArrayList<>();
            categories.forEach(
                    category -> {
                        MovieCategory movieCategory = new MovieCategory();
                        movieCategory.setMovie(newMovie);
                        movieCategory.setCategory(category);
                        movieCategories.add(movieCategory);
                    }
            );
            movieCategoryRepository.saveAll(movieCategories);
        }
        return ResponseObject.successForward(
                HttpStatus.CREATED,
                Message.Success.CREATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> updateMovie(String id, AdminMovieRequest request) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + ", phim"
            );
        }
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPicture(request.getPictureURL());
        movie.setMovies(request.getMoviesURL());
        movie.setAuthor(request.getAuthor());
        movie.setActor(request.getActor());
        movie.setYear(request.getYear());
        Movie newMovie = movieRepository.save(movie);
        movieCategoryRepository.deleteAllByMovie(movie);
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if (!categories.isEmpty()) {
            List<MovieCategory> movieCategories = new ArrayList<>();
            categories.forEach(
                    category -> {
                        MovieCategory movieCategory = new MovieCategory();
                        movieCategory.setMovie(newMovie);
                        movieCategory.setCategory(category);
                        movieCategories.add(movieCategory);
                    }
            );
            movieCategoryRepository.saveAll(movieCategories);
        }
        return ResponseObject.successForward(
                HttpStatus.OK,
                Message.Success.UPDATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> changeStatusMovieById(String id) {
        try {
            Optional<Movie> movieOptional = movieRepository.findById(id);
            if (movieOptional.isEmpty()) {
                return ResponseObject.errorForward(
                        HttpStatus.BAD_REQUEST,
                        Message.Response.NOT_FOUND + ", movie"
                );
            }
            Movie newMovie = movieOptional.get();
            newMovie.setDeleted(!newMovie.getDeleted());
            movieRepository.save(newMovie);
            return ResponseObject.successForward(
                    HttpStatus.CREATED,
                    Message.Success.UPDATE_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
