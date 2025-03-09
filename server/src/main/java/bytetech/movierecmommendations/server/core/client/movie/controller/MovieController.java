package bytetech.movierecmommendations.server.core.client.movie.controller;

import bytetech.movierecmommendations.server.core.client.movie.model.request.MovieFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.service.MovieService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(MappingConstant.API_USER_MOVIE)

//@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject<Page<Movie>>> getAll(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "categoryId", required = false) String categoryId,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "actor", required = false) String actor,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        MovieFilterRequest filterRequest = new MovieFilterRequest(title, categoryId, description, author, actor, year);
        ResponseObject<Page<Movie>> response = movieService.getAll(filterRequest, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id) {
        return Helper.createResponseEntity(movieService.getMovieById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchMovies(@RequestParam String keyword) {
        List<Movie> movies = movieService.searchMovie(keyword);
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject<>(null, HttpStatus.NOT_FOUND, "Không tìm thấy phim nào"));
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Movie>> filterMovies(@RequestParam String sortBy) {
        List<Movie> movies = movieService.filterMovies(sortBy);
        return ResponseEntity.ok(movies);
    }
}
