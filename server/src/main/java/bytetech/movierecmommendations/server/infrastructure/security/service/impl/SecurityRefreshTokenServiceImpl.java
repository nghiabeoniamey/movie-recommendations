package bytetech.movierecmommendations.server.infrastructure.security.service.impl;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.RefreshToken;
import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthForgotPassRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthLoginRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthRefreshRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthRegisterRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthVerifyRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.response.AuthRefreshResponse;
import bytetech.movierecmommendations.server.infrastructure.security.model.response.TokenUriResponse;
import bytetech.movierecmommendations.server.infrastructure.security.repository.SecurityRefreshRepository;
import bytetech.movierecmommendations.server.infrastructure.security.repository.SecurityUserRepository;
import bytetech.movierecmommendations.server.infrastructure.security.service.RefreshTokenService;
import bytetech.movierecmommendations.server.infrastructure.security.service.SecurityEmailService;
import bytetech.movierecmommendations.server.infrastructure.security.service.SecurityRefreshTokenService;
import bytetech.movierecmommendations.server.infrastructure.security.service.TokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class SecurityRefreshTokenServiceImpl implements SecurityRefreshTokenService {

    private final TokenProvider tokenProvider;

    private final SecurityRefreshRepository authRefreshTokenRepository;

    private final SecurityUserRepository authUserRepository;

    private final RefreshTokenService refreshTokenService;

    private final SecurityEmailService emailService;

    @Override
    public ResponseObject<?> getRefreshToken(@Valid AuthRefreshRequest request) {
        try {
            String refreshToken = request.getRefreshToken();

            Optional<RefreshToken> refreshTokenOptional = authRefreshTokenRepository.findByRefreshToken(refreshToken);
            if (refreshTokenOptional.isEmpty()) {
                return ResponseObject.errorForward(HttpStatus.NOT_FOUND, "Refresh token not found");
            }

            RefreshToken refreshTokenEntity = refreshTokenOptional.get();
            if (refreshTokenEntity.getRevokedAt() != null) {
                return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "Refresh token has been revoked");
            }

            String accessToken = tokenProvider.createToken(refreshTokenEntity.getUserId());
            return ResponseObject.successForward(new AuthRefreshResponse(accessToken, refreshToken), "Get refresh token successfully");
        } catch (Exception e) {
            return ResponseObject.errorForward(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    public ResponseObject<?> logout(@Valid AuthRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        Optional<RefreshToken> refreshTokenOptional = authRefreshTokenRepository.findByRefreshToken(refreshToken);
        if (refreshTokenOptional.isEmpty()) {
            return ResponseObject.errorForward(HttpStatus.NOT_FOUND, "Refresh token not found");
        }

        RefreshToken refreshTokenEntity = refreshTokenOptional.get();
        refreshTokenEntity.setRevokedAt(System.currentTimeMillis());
        authRefreshTokenRepository.save(refreshTokenEntity);

        return ResponseObject.successForward(null, "Logout successfully");
    }

    @Override
    public ResponseObject<?> login(AuthLoginRequest request) {
        try {
            Optional<User> userOptional = authUserRepository.findByEmailAndDeleted(request.getEmail(), false);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (request.getPassword().equalsIgnoreCase(user.getPassword())) {
                    String accessToken = tokenProvider.createToken(user.getId());
                    String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getRefreshToken();
                    return ResponseObject.successForward(TokenUriResponse.getState(accessToken, refreshToken), "Get state successfully");

                }
                return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "Incorrect password");
            }
            return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "User does not exits");
        } catch (Exception e) {
            log.info("😢😢 ~> Error login");
            return ResponseObject.errorForward(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseObject<?> register(AuthRegisterRequest request) {
        try {
            Optional<User> userOptional = authUserRepository.findByEmail(request.getEmail());
            if (userOptional.isPresent()) {
                return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "Email already in use");
            }
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setRoleConstant(RoleConstant.USER);
            user.setDeleted(true);
            User newUser = authUserRepository.save(user);
            String accessToken = tokenProvider.createToken(newUser.getId());
            emailService.sendMailVerified(newUser, accessToken);
            return ResponseObject.successForward(null, "Đăng ký thành công, vui lòng xác thực email để kích hoạt tài khoản");

        } catch (Exception e) {
            log.info("😢😢 ~> Error encrypt register");
            return ResponseObject.errorForward(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseObject<?> verify(AuthVerifyRequest request) {
        String email = tokenProvider.getEmailFromToken(request.getToken());
        try {
            Optional<User> userOptional = authUserRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setDeleted(false);
                authUserRepository.save(user);
                return ResponseObject.successForward(
                        user, "Xác thực tài khoản thành công bạn có thể đăng nhập vào hệ thống"
                );
            }
            return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "User does not exits");
        } catch (Exception e) {
            log.info("😢😢 ~> Error encrypt verify");
            return ResponseObject.errorForward(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseObject<?> forgotPassword(AuthForgotPassRequest request) {
        try {
            Optional<User> userOptional = authUserRepository.findByEmailAndDeleted(request.getEmail(), false);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String accessToken = tokenProvider.createToken(user.getId());
                emailService.sendMailForgotPass(user, accessToken);
                return ResponseObject.successForward(
                        user, "Gửi mail lấy lại tài khoản thành công!"
                );
            }
            return ResponseObject.errorForward(HttpStatus.BAD_REQUEST, "Incorrect email: " + request.getEmail() + " not exists on system");
        } catch (Exception e) {
            log.info("😢😢 ~> Error encrypt forgot password");
            return ResponseObject.errorForward(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
