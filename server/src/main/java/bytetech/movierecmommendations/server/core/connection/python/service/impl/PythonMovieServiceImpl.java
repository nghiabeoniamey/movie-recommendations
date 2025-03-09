package bytetech.movierecmommendations.server.core.connection.python.service.impl;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.core.connection.python.repository.PythonMovieRepository;
import bytetech.movierecmommendations.server.core.connection.python.service.PythonMovieService;
import bytetech.movierecmommendations.server.core.connection.python.service.PythonWebClientService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PythonMovieServiceImpl implements PythonMovieService {

    private final PythonWebClientService webClientService;

    private final PythonMovieRepository movieRepository;

    public PythonMovieServiceImpl(PythonWebClientService webClientService, PythonMovieRepository movieRepository) {
        this.webClientService = webClientService;
        this.movieRepository = movieRepository;
    }

    @Override
    public ResponseObject<?> getMoviesRecommendation() {
        List<String> movieIds = webClientService.callApiGetMoviesRecommendation();
        if (movieIds != null && !movieIds.isEmpty()) {
            return new ResponseObject<>(
                    movieRepository.getMoviesByMovieIds(movieIds),
                    HttpStatus.OK,
                    Message.Success.GET_SUCCESS
            );
        }
        return new ResponseObject<>(
                Collections.EMPTY_LIST,
                HttpStatus.OK,
                Message.Success.GET_SUCCESS
        );
    }

}
