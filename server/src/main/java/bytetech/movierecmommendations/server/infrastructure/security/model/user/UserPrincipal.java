package bytetech.movierecmommendations.server.infrastructure.security.model.user;

import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserPrincipal implements  UserDetails {

    @Getter
    private final String id;

    @Getter
    private final String email;

    private final Collection<? extends GrantedAuthority> authorities;

    private String password;

    @Setter
    private Map<String, Object> attributes;

    public UserPrincipal(String id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(RoleConstant.ADMIN.name()));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                authorities
        );
    }

    public static UserPrincipal create(User user, String role) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(role));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes, String roles) {
        UserPrincipal userPrincipal = UserPrincipal.create(user, roles);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
