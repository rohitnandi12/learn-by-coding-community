package com.bms.monolith_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_show")
public class EventShow {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "show_genre", joinColumns = @JoinColumn(name = "show_id"))
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    private Instant startDate;
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "show")
    private List<SeatInShow> seatInShows;

    @OneToMany(mappedBy = "show")
    private List<SeatTypeInShow> seatTypeInShows;
}
