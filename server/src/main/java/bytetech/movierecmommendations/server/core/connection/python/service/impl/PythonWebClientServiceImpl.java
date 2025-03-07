package bytetech.movierecmommendations.server.core.connection.python.service.impl;

import bytetech.movierecmommendations.server.core.connection.python.model.response.PythonMovieIdsResponse;
import bytetech.movierecmommendations.server.core.connection.python.service.PythonWebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PythonWebClientServiceImpl implements PythonWebClientService {

    @Value("${python.url}")
    private String api;

    private final WebClient webClient;

    public PythonWebClientServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public List<String> callApiGetMoviesRecommendation() {
        String url = api + "recommendations";
        try {
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(PythonMovieIdsResponse.class)
                    .map(PythonMovieIdsResponse::getRecommendedMovieIds)
                    .defaultIfEmpty(Collections.emptyList())
                    .block();
        } catch (WebClientResponseException e) {
            log.error("API error: {} - {}", e.getRawStatusCode(), e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            log.error("Unexpected error when calling API: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

}