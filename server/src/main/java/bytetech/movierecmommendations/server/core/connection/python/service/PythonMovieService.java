package bytetech.movierecmommendations.server.core.connection.python.service;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;

public interface PythonMovieService {

    ResponseObject<?> getMoviesRecommendation();

}
