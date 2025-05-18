package bytetech.movierecmommendations.server.core.admin.user.model.request;

import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRequest {

    private String name;

    private String email;

    private String password;

    private String profilePicture;

    private Integer role;

}
