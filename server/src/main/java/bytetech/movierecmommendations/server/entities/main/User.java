package bytetech.movierecmommendations.server.entities.main;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import bytetech.movierecmommendations.server.infrastructure.constants.module.EntityProperties;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import jakarta.persistence.Column;
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
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends PrimaryEntity implements Serializable {

    @Column(name = "full_name", length = EntityProperties.LENGTH_NAME)
    private String fullName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(length = EntityProperties.LENGTH_CODE, unique = true)
    private String email;

    @Column(name = "password", length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Column(name = "address", length = EntityProperties.LENGTH_CONTENT)
    private String address;

    @Column(length = EntityProperties.LENGTH_URL)
    private String profilePicture;

    @Enumerated(EnumType.ORDINAL)
    private RoleConstant roleConstant;

}
