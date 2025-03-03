package bytetech.movierecmommendations.server.repository;

import bytetech.movierecmommendations.server.entities.main.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
