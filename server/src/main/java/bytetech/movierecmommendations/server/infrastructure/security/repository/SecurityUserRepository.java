package bytetech.movierecmommendations.server.infrastructure.security.repository;

import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityUserRepository extends UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndDeleted(String email, Boolean deleted);

}
