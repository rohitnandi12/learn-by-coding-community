package org.lbcc.bms.bms_monolith.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.experimental.SuperBuilder;
import org.lbcc.bms.bms_monolith.common.entity.BaseAuditingEntity;

import java.math.BigDecimal;

@Entity
@SuperBuilder
public class SeatTypeInShow extends BaseAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private EventShow show;

    private BigDecimal price;
}
