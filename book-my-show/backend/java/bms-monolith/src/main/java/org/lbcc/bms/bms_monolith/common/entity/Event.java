package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "events")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class Event extends BaseAuditingEntity {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventShow> show;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @Column(nullable = false)
    private Instant startDate;

    @Column // default 255 in length
    private String thumbnailUrl;

    @Column(nullable = false)
    private Instant endDate;

}
