package bytetech.movierecmommendations.server.core.client.movie.model.request;

import bytetech.movierecmommendations.server.infrastructure.constants.module.EntityProperties;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieFilterRequest {

    private String title;

    private String categoryId;

    private String description;

    private String author;

    private String actor;

    private Integer year;

    @Column(length = EntityProperties.LENGTH_URL)
    private String picture;

    @Column(length = EntityProperties.LENGTH_URL)
    private String movies;

}
