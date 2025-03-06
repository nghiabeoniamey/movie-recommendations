package bytetech.movierecmommendations.server.core.connection.python.service;

import java.util.List;

public interface PythonWebClientService {

    List<String> callApiGetMoviesRecommendation();

}
