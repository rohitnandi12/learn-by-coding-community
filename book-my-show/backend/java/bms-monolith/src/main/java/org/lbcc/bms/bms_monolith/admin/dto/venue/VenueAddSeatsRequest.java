package org.lbcc.bms.bms_monolith.admin.dto.venue;

import lombok.Data;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatDto;

import java.util.List;

@Data
public class VenueAddSeatsRequest {
    private String venueId;
    private List<SeatDto> seatDtos;
}
