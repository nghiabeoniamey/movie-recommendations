package bytetech.movierecmommendations.server.core.user.review.repository;

import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.repository.WatchHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewWatchHistoryRepository extends WatchHistoryRepository {

    boolean existsWatchHistoriesByUserAndMovie(User user, Movie movie);

}
