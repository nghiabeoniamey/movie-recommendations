package bytetech.movierecmommendations.server.core.admin.movie.controller;

import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminFindMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.service.AdminMovieService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MappingConstant.API_ADMIN_MOVIE)
@RequiredArgsConstructor
@RestController
public class AdminMovieController {

    private final AdminMovieService movieService;

    @GetMapping()
    public ResponseEntity<?> getMovies(@Valid final AdminFindMovieRequest request) {
        return Helper.createResponseEntity(movieService.getMovie(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id) {
        return Helper.createResponseEntity(movieService.getMovieById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createMovie(@Valid @RequestBody final AdminMovieRequest request) {
        return Helper.createResponseEntity(movieService.createMovie(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable String id, @Valid @RequestBody final AdminMovieRequest request) {
        return Helper.createResponseEntity(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable String id) {
        return Helper.createResponseEntity(movieService.changeStatusMovieById(id));
    }

}
