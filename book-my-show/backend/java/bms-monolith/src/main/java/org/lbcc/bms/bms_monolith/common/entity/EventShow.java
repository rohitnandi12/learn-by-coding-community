package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.lbcc.bms.bms_monolith.common.enums.Genre;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "event_shows")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EventShow extends BaseAuditingEntity {

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "show_genres", joinColumns = @JoinColumn(name = "show_id"))
    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatInShow> seatInShows;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatTypeInShow> seatTypeInShows;
}
