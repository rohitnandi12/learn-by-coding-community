package com.bms.monolith_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer totalSeatingCapacity;

    @Enumerated(EnumType.STRING)
    private VenueType venueType;

    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;

    @OneToMany(mappedBy = "venue")
    private List<Seat> seats;
}
