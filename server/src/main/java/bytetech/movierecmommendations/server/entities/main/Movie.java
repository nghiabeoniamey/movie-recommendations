package bytetech.movierecmommendations.server.entities.main;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import bytetech.movierecmommendations.server.infrastructure.constants.module.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie extends PrimaryEntity implements Serializable {

    private String title;

    private String description;

    private String author;

    private String actor;

    private Integer year;

    @Column(length = EntityProperties.LENGTH_URL)
    private String picture;

    @Column(length = EntityProperties.LENGTH_URL)
    private String movies;

}
