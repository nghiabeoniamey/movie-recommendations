package bytetech.movierecmommendations.server.infrastructure.security.service;

import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthForgotPassRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthLoginRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthRefreshRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthRegisterRequest;
import bytetech.movierecmommendations.server.infrastructure.security.model.request.AuthVerifyRequest;
import jakarta.validation.Valid;

public interface SecurityRefreshTokenService {

    ResponseObject<?> getRefreshToken(@Valid AuthRefreshRequest request);

    ResponseObject<?> logout(@Valid AuthRefreshRequest request);

    ResponseObject<?> login(@Valid AuthLoginRequest request);

    ResponseObject<?> register(@Valid AuthRegisterRequest request);

    ResponseObject<?> verify(@Valid AuthVerifyRequest request);

    ResponseObject<?> forgotPassword(@Valid AuthForgotPassRequest request);

}
