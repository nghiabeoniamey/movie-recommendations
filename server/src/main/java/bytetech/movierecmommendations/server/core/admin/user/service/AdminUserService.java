package bytetech.movierecmommendations.server.core.admin.user.service;

import bytetech.movierecmommendations.server.core.admin.user.model.request.AdminFindUserRequest;
import bytetech.movierecmommendations.server.core.admin.user.model.request.AdminUserRequest;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import jakarta.validation.Valid;

public interface AdminUserService {

    ResponseObject<?> getUsers(AdminFindUserRequest request);

    ResponseObject<?> getUserById(String id);

    ResponseObject<?> createUser(@Valid AdminUserRequest request);

    ResponseObject<?> updateUser(String id, @Valid AdminUserRequest request);

    ResponseObject<?> changeStatusUser(String id);

}
