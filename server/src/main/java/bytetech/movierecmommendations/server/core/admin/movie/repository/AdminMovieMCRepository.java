package bytetech.movierecmommendations.server.core.admin.movie.repository;

import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.repository.MovieCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMovieMCRepository extends MovieCategoryRepository {

    @Transactional
    void deleteAllByMovie(Movie movie);

}
