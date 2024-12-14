package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.Data;

@Data
public class SeatInShowRequest {
    private String seatTypeInShowId;
    private String bookingStatus;
}