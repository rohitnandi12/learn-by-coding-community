package com.bms.monolith_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Venue venue;

    @OneToMany(mappedBy = "event")
    private List<EventShow> shows;

    @ManyToOne
    private EventType eventType;

    private Instant startDate;

    private String thumbnailUrl;

    private Instant endDate;

}
