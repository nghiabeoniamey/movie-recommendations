package bytetech.movierecmommendations.server.core.user.account.service;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.core.user.account.model.request.UserFindAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.model.request.UserAccountRequest;
import jakarta.validation.Valid;

public interface UserAccountService {

    ResponseObject<?> getUsers(UserFindAccountRequest request);

    ResponseObject<?> getUserById(String id);

    ResponseObject<?> createUser(@Valid UserAccountRequest request);

    ResponseObject<?> updateUser(String id, @Valid UserAccountRequest request);

    ResponseObject<?> changeStatusUser(String id);

}
