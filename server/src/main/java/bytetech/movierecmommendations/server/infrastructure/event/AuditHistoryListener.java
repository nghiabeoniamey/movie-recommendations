package bytetech.movierecmommendations.server.infrastructure.event;

import bytetech.movierecmommendations.server.entities.base.AuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditHistoryListener {

    @PrePersist
    private void onCreate(AuditEntity entity) {
        entity.setCreatedBy(null);
        entity.setLastModifiedBy(null);
    }

    @PreUpdate
    private void onUpdate(AuditEntity entity) {
        entity.setLastModifiedBy(null);
    }

}
