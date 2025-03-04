package bytetech.movierecmommendations.server.infrastructure.security.service;

import bytetech.movierecmommendations.server.entities.main.User;

public interface SecurityEmailService {

    void sendMailVerified(User newUser, String accessToken);

    void sendMailForgotPass(User newUser, String accessToken);

}
