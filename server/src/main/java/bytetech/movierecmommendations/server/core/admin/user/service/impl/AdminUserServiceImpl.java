package bytetech.movierecmommendations.server.core.admin.user.service.impl;

import bytetech.movierecmommendations.server.core.admin.user.model.request.AdminFindUserRequest;
import bytetech.movierecmommendations.server.core.admin.user.model.request.AdminUserRequest;
import bytetech.movierecmommendations.server.core.admin.user.repository.AdminUserRepository;
import bytetech.movierecmommendations.server.core.admin.user.service.AdminUserService;
import bytetech.movierecmommendations.server.core.common.base.PageableObject;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository userRepository;

    @Override
    public ResponseObject<?> getUsers(AdminFindUserRequest request) {
        Pageable pageable = Helper.createPageable(request);
        return new ResponseObject<>(
                PageableObject.of(userRepository.getUsers(pageable, request)),
                HttpStatus.OK,
                Message.Success.GET_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> getUserById(String id) {
        return new ResponseObject<>(
                userRepository.findUserById(id),
                HttpStatus.OK,
                Message.Success.GET_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> createUser(AdminUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.DUPLICATE + ", email"
            );
        }
        User newUser = new User();
        newUser.setFullName(request.getUserName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setProfilePicture(request.getProfilePicture());
        RoleConstant role = request.getRole() == 0 ? RoleConstant.ADMIN : RoleConstant.USER;
        newUser.setRoleConstant(role);
        newUser.setDeleted(false);
        userRepository.save(newUser);
        return ResponseObject.successForward(
                HttpStatus.CREATED,
                Message.Success.CREATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> updateUser(String id, AdminUserRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + ", người dùng"
            );
        }
        User newUser = userOptional.get();
        if (!newUser.getEmail().equals(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.DUPLICATE + ", email"
            );
        }
        newUser.setFullName(request.getUserName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setProfilePicture(request.getProfilePicture());
        RoleConstant role = request.getRole() == 0 ? RoleConstant.ADMIN : RoleConstant.USER;
        newUser.setRoleConstant(role);
        userRepository.save(newUser);
        return ResponseObject.successForward(
                HttpStatus.CREATED,
                Message.Success.UPDATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> changeStatusUser(String id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseObject.errorForward(
                        HttpStatus.BAD_REQUEST,
                        Message.Response.NOT_FOUND + ", người dùng"
                );
            }
            User newUser = userOptional.get();
            newUser.setDeleted(!newUser.getDeleted());
            userRepository.save(newUser);
            return ResponseObject.successForward(
                    HttpStatus.CREATED,
                    Message.Success.UPDATE_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
