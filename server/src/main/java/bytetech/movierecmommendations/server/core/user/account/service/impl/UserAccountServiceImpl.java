package bytetech.movierecmommendations.server.core.user.account.service.impl;

import bytetech.movierecmommendations.server.core.common.base.PageableObject;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.core.user.account.model.request.UpdateAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.model.request.UserFindAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.model.request.UserAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.repository.UserAccountRepository;
import bytetech.movierecmommendations.server.core.user.account.service.UserAccountService;
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
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepository;

    @Override
    public ResponseObject<?> getUsers(UserFindAccountRequest request) {
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
    public ResponseObject<?> createUser(UserAccountRequest request) {
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
    public ResponseObject<?> updateUser(String id, UpdateAccountRequest request) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseObject.errorForward(
                        HttpStatus.BAD_REQUEST,
                        Message.Response.NOT_FOUND + ", người dùng"
                );
            }
            User newUser = userOptional.get();
            newUser.setFullName(request.getName());
            newUser.setProfilePicture(request.getProfilePicture());
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
