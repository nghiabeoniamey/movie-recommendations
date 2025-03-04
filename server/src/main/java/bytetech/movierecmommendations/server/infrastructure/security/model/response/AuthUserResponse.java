package bytetech.movierecmommendations.server.infrastructure.security.model.response;

public interface AuthUserResponse {

    String fullName();

    String email();

    String businessName();

    String businessTypeId();

    String password();

}
