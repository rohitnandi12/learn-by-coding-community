package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatTypeInShowRequest {
    private String seatTypeId;
    private BigDecimal price;
}