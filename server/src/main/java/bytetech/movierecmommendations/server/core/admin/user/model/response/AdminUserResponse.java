package bytetech.movierecmommendations.server.core.admin.user.model.response;

import bytetech.movierecmommendations.server.core.common.base.BaseResponse;

public interface AdminUserResponse extends BaseResponse {

    String getName();

    String getEmail();

    String getPassword();

    String getProfilePicture();

    Integer getRole();

    Boolean getDeleted();

}
