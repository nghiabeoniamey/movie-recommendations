package bytetech.movierecmommendations.server.repository;

import bytetech.movierecmommendations.server.entities.main.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends CrudRepository<Reviewer, String> {
}
