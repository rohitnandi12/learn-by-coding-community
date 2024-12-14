package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "seat_type_in_shows")
@Getter
@SuperBuilder
@Data
public class SeatTypeInShow extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "seat_type_id", nullable = false)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private EventShow show;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}
