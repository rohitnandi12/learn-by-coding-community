package org.lbcc.bms.bms_monolith.eventservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypeInShowDTO;
import org.lbcc.bms.bms_monolith.common.entity.*;
import org.lbcc.bms.bms_monolith.common.enums.Genre;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventShowDTO {

    private String id;

    @NotNull(message = "Show date is required.")
    private Instant showDate;

    @NotNull(message = "Start time is required.")
    private Instant startTime;

    @NotNull(message = "End time is required.")
    private Instant endTime;

    private List<Genre> genres;

    private List<SeatTypeInShowDTO> seatTypeInShows;

    //hiding below fields from client side
    @JsonIgnore
    private List<Seat> seats;

    @JsonIgnore
    private Map<String, SeatType> seatTypeMap;
}