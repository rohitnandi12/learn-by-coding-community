package org.lbcc.bms.bms_monolith.eventservice.dto.seat;

import lombok.Data;
import lombok.Getter;
import org.lbcc.bms.bms_monolith.common.entity.SeatInShow;

import java.util.UUID;

@Data
@Getter
public class SeatInShowResponse {

    private UUID seatTypeInShowId;
    private String bookingStatus;

    // Constructors, Getters, and Setters

    public SeatInShowResponse(UUID seatTypeInShowId, String bookingStatus) {
        this.seatTypeInShowId = seatTypeInShowId;
        this.bookingStatus = bookingStatus;
    }

    // Static factory method for conversion from SeatInShow entity to SeatInShowResponse DTO
    public static SeatInShowResponse fromEntity(SeatInShow seatInShow) {
        return new SeatInShowResponse(
                seatInShow.getSeatTypeInShow() != null ? seatInShow.getSeatTypeInShow().getId() : null,
                seatInShow.getBookingStatus() != null ? seatInShow.getBookingStatus().name() : null
        );
    }
}

