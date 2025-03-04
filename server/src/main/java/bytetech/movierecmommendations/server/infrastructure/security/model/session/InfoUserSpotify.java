package bytetech.movierecmommendations.server.infrastructure.security.model.session;


import bytetech.movierecmommendations.server.infrastructure.security.model.response.InfoUserResponse;

public interface InfoUserSpotify {

    String getId();

    String getUserName();

    String getEmail();

    String getProfilePicture();

    String getRoleCode();

    String getRoleName();

    String getHost();

    InfoUserResponse getInfoUser();

}
