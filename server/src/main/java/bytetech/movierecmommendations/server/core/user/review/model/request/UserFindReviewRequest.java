package bytetech.movierecmommendations.server.core.user.review.model.request;

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
public class UserFindReviewRequest extends PageableRequest {

    private String keyword;

    private String movieId;

    private Integer rating;

    private Long startDate;

    private Long endDate;

}
