package bytetech.movierecmommendations.server.core.client.movei.model.request;

import bytetech.movierecmommendations.server.infrastructure.constants.module.EntityProperties;
import jakarta.persistence.Column;

public class MoviFilterRequest {
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
