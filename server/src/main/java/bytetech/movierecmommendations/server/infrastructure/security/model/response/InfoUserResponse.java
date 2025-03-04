package bytetech.movierecmommendations.server.infrastructure.security.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfoUserResponse {

    private String id;

    private String userName;

    private String email;

    private String profilePicture;

    private String host;

    private String roleCode;

    private String roleName;

}
