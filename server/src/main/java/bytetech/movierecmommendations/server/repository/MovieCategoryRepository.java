package bytetech.movierecmommendations.server.repository;

import bytetech.movierecmommendations.server.entities.main.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, String> {
}
