package com.bms.monolith_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatInShow {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private SeatTypeInShow seatTypeInShow;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private EventShow show;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
