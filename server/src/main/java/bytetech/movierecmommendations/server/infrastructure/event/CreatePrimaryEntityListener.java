package bytetech.movierecmommendations.server.infrastructure.event;

import bytetech.movierecmommendations.server.entities.base.PrimaryEntity;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class CreatePrimaryEntityListener {

    @PrePersist
    private void onCreate(PrimaryEntity entity) {
        entity.setId(UUID.randomUUID().toString());
    }

}
