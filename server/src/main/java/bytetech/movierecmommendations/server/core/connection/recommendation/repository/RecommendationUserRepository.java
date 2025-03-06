package bytetech.movierecmommendations.server.core.connection.recommendation.repository;

import bytetech.movierecmommendations.server.core.connection.recommendation.model.response.RecommendationUserResponse;
import bytetech.movierecmommendations.server.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationUserRepository extends UserRepository {

    @Query(value = """
            SELECT
                  u.id AS id,
                  u.address AS address
            FROM user u
            WHERE u.deleted = false
            """, nativeQuery = true)
    List<RecommendationUserResponse> getUsers();

}
