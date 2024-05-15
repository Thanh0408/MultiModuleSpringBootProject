package demo.com.vn.entity;

import demo.com.vn.enums.EnumIsDelete;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractAuditEntity {

    @CreatedBy
    @Column(name = "CREATED_BY")
    String createdBy;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    String lastModifiedBy;

    @UpdateTimestamp
    @Column(name = "LAST_MODIFIED_ON")
    ZonedDateTime lastModifiedOn;

    @CreationTimestamp
    @Column(name = "CREATED_ON")
    ZonedDateTime createdOn;

    @Column(name = "IS_DELETED")
    Integer isDeleted = EnumIsDelete.ACTIVE.getCode();
}
