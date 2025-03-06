package bytetech.movierecmommendations.server.core.admin.movie.model.request;

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
public class AdminFindMovieRequest extends PageableRequest {

    private String keyword;

    private String[] categoryId;

    private Long startDate;

    private Long endDate;

}
