package bytetech.movierecmommendations.server.core.user.account.controller;

import bytetech.movierecmommendations.server.core.user.account.model.request.UpdateAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.model.request.UserAccountRequest;
import bytetech.movierecmommendations.server.core.user.account.service.UserAccountService;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import bytetech.movierecmommendations.server.util.Helper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(MappingConstant.API_USER_ACCOUNT)
@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final UserAccountService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        return Helper.createResponseEntity(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody final UserAccountRequest request) {
        return Helper.createResponseEntity(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody final UpdateAccountRequest request) {
        return Helper.createResponseEntity(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> changeStatusUser(@PathVariable String id) {
        return Helper.createResponseEntity(userService.changeStatusUser(id));
    }

}
