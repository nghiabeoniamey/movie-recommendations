package bytetech.movierecmommendations.server.infrastructure.database.repository;

import bytetech.movierecmommendations.server.repository.MovieRepository;
import bytetech.movierecmommendations.server.repository.ReviewerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBGenReviewRepository extends ReviewerRepository {
}
