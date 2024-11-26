package org.lbcc.bms.bms_monolith.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {



    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant lastModifiedDate;

    @CreatedBy
    @Column(updatable = false, length = 10)
    private String createdBy;

    @LastModifiedBy
    @Column(insertable = false, length = 10)
    private String lastModifiedBy;
}
