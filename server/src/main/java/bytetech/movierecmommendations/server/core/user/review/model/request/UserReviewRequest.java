package bytetech.movierecmommendations.server.core.user.review.model.request;

import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewRequest {

    @NotBlank(message = Message.Validate.NOT_BLANK)
    private String movieId;

    private Integer rating;

    @NotBlank(message = Message.Validate.NOT_BLANK)
    private String comment;

}
