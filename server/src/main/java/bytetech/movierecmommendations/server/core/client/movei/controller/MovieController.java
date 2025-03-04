package bytetech.movierecmommendations.server.core.client.movei.controller;

import bytetech.movierecmommendations.server.core.client.movei.service.MovieService;
import bytetech.movierecmommendations.server.entities.main.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam String keyword) {
        List<Movie> rooms = movieService.searchMovie(keyword);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Movie>> filterMovies(@RequestParam String sortBy) {
        List<Movie> movies = movieService.filterMovies(sortBy);
        return ResponseEntity.ok(movies);
    }
}
