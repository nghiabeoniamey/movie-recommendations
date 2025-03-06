package bytetech.movierecmommendations.server.core.connection.python.controller;

import bytetech.movierecmommendations.server.core.connection.python.service.PythonMovieService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MappingConstant.API_CONNECTION_PYTHON)
@RequiredArgsConstructor
@RestController
public class PythonWebClientController {

    private final PythonMovieService movieService;

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        return Helper.createResponseEntity(movieService.getMoviesRecommendation());
    }

}
