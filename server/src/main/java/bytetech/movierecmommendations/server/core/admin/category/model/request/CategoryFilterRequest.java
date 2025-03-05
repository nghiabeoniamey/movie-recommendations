package bytetech.movierecmommendations.server.core.admin.category.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFilterRequest {

    private String name;

    private String code;

    private Long movieId;

}
