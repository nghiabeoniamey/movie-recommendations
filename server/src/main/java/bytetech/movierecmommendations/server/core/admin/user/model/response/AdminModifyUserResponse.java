package bytetech.movierecmommendations.server.core.admin.user.model.response;

public interface AdminModifyUserResponse {

    String getId();

    String getName();

    String getEmail();

    String getPassword();

    String getProfilePicture();

    Integer getRole();

    Boolean getDeleted();

}
