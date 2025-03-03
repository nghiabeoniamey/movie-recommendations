package bytetech.movierecmommendations.server.core.admin.user.model.request;

import bytetech.movierecmommendations.server.core.common.base.PageableRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminFindUserRequest extends PageableRequest {

    private String keyword;

    private Integer status;

    private Integer role;

}
