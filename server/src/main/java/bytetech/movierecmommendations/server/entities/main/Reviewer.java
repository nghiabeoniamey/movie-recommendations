package bytetech.movierecmommendations.server.entities.main;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "reviewer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reviewer extends PrimaryEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

}
