package bytetech.movierecmommendations.server.core.admin.category.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CategoryFilterRequest {
    private String name;
    private String code;
    private Long movieId;
}
