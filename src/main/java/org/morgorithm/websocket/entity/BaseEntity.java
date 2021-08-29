package org.morgorithm.websocket.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.morgorithm.websocket.configuration.LocalDateTimeAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Setter
@DynamicInsert
abstract class BaseEntity {
    @CreatedDate
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "regdate", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;
}
