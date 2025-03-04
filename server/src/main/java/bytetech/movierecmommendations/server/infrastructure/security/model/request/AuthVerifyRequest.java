package bytetech.movierecmommendations.server.infrastructure.security.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthVerifyRequest {

    @NotNull(message = "Token is required")
    @NotBlank(message = "Token is required")
    private String token;

}
