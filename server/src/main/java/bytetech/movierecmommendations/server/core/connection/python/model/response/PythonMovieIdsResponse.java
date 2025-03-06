package bytetech.movierecmommendations.server.core.connection.python.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PythonMovieIdsResponse {

    private List<String> recommendedMovieIds;

}
