package bytetech.movierecmommendations.server.entities.main;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends PrimaryEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private RoleConstant role;

    private String description;

}
