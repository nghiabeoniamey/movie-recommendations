package bytetech.movierecmommendations.server.infrastructure.event;

import bytetech.movierecmommendations.server.entities.base.AuditEntity;
import bytetech.movierecmommendations.server.util.AuditorProviderByAuthenticationUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditHistoryListener {

    @PrePersist
    private void onCreate(AuditEntity entity) {
        entity.setCreatedBy(AuditorProviderByAuthenticationUtil.getUserId());
        entity.setLastModifiedBy(AuditorProviderByAuthenticationUtil.getUserId());
    }

    @PreUpdate
    private void onUpdate(AuditEntity entity) {
        entity.setLastModifiedBy(AuditorProviderByAuthenticationUtil.getUserId());
    }

}
