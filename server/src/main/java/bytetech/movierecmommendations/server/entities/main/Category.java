package bytetech.movierecmommendations.server.entities.main;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends PrimaryEntity implements Serializable {

    private String name;

    private String description;

}
