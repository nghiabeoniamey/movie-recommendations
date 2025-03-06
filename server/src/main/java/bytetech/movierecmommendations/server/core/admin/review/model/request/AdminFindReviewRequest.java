package bytetech.movierecmommendations.server.core.admin.review.model.request;

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
public class AdminFindReviewRequest extends PageableRequest {

    private String keyword;

    private String movie;

    private Integer rating;

    private Long startDate;

    private Long endDate;

}
