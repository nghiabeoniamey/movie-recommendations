package bytetech.movierecmommendations.server.entities.base;

import bytetech.movierecmommendations.server.infrastructure.event.AuditEntityListener;
import bytetech.movierecmommendations.server.infrastructure.event.AuditHistoryListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditEntityListener.class, AuditHistoryListener.class})
public abstract class AuditEntity {

    @Column(name = "created_date")
    @CreatedDate
    private Long createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Long lastModifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "deleted", precision = 0)
    private Boolean deleted = false;


}