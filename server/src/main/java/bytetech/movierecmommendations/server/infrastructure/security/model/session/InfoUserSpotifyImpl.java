package bytetech.movierecmommendations.server.infrastructure.security.model.session;

import bytetech.movierecmommendations.server.infrastructure.constants.auth.Session;
import bytetech.movierecmommendations.server.infrastructure.security.model.response.InfoUserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfoUserSpotifyImpl implements InfoUserSpotify {

    private final HttpSession httpSession;

    @Override
    public String getId() {
        return httpSession.getAttribute(Session.CURRENT_USER_ID).toString();
    }

    @Override
    public String getUserName() {
        return httpSession.getAttribute(Session.CURRENT_USER_NAME).toString();
    }

    @Override
    public String getEmail() {
        return httpSession.getAttribute(Session.CURRENT_USER_EMAIL).toString();
    }

    @Override
    public String getProfilePicture() {
        return httpSession.getAttribute(Session.CURRENT_USER_PROFILE_PICTURE).toString();
    }

    @Override
    public String getRoleCode() {
        return httpSession.getAttribute(Session.CURRENT_USER_ROLE_CODE).toString();
    }

    @Override
    public String getRoleName() {
        return httpSession.getAttribute(Session.CURRENT_USER_ROLE_NAME).toString();
    }

    @Override
    public String getHost() {
        return httpSession.getAttribute(Session.CURRENT_HOST).toString();
    }

    @Override
    public InfoUserResponse getInfoUser() {
        return new InfoUserResponse(
                getId(),
                getUserName(),
                getEmail(),
                getProfilePicture(),
                getRoleCode(),
                getRoleName(),
                getHost()
        );
    }
}
