package bytetech.movierecmommendations.server.repository;

import bytetech.movierecmommendations.server.entities.main.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, String> {
}
