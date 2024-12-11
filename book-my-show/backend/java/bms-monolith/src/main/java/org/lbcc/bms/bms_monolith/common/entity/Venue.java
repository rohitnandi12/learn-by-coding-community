package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lbcc.bms.bms_monolith.common.enums.OperationalStatus;
import org.lbcc.bms.bms_monolith.common.enums.VenueType;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "venues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue extends BaseAuditingEntity {

    @Column(nullable = false, unique = true)
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer totalSeatingCapacity;

    @Enumerated(EnumType.STRING)
    private VenueType venueType;

    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<Seat> seats;
}
