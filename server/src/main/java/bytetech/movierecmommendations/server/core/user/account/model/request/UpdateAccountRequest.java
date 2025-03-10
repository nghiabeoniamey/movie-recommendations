package bytetech.movierecmommendations.server.core.user.account.model.request;

import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {
    @NotBlank(message = Message.Validate.NOT_BLANK)
    @Size(min = 8, max = 50, message = Message.Validate.SIZE50)
    private String name;

    @NotBlank(message = Message.Validate.NOT_BLANK)
    @Size(min = 8, max = 100, message = Message.Validate.SIZE100)
    private String profilePicture;


}
