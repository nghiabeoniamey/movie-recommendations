package bytetech.movierecmommendations.server.infrastructure.security.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthForgotPassRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

}
