package bytetech.movierecmommendations.server.infrastructure.security.service;

import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.security.model.user.UserPrincipal;
import bytetech.movierecmommendations.server.infrastructure.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final SecurityUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String role = user.getRoleConstant().name();
            return UserPrincipal.create(user, role);
        }

        throw new UsernameNotFoundException("user not found with email : " + email);
    }

}