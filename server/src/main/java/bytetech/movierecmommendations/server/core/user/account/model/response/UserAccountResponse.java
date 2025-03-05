package bytetech.movierecmommendations.server.core.user.account.model.response;

import bytetech.movierecmommendations.server.core.common.base.BaseResponse;

public interface UserAccountResponse extends BaseResponse {

    String getName();

    String getEmail();

    String getPassword();

    String getProfilePicture();

    Integer getRole();

    Boolean getDeleted();

}
