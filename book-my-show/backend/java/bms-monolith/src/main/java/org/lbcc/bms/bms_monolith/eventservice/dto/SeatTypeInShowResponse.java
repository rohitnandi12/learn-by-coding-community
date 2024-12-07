package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.Getter;
import org.lbcc.bms.bms_monolith.common.entity.SeatTypeInShow;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class SeatTypeInShowResponse {

    private String seatTypeId;
    private String seatTypeName;
    private BigDecimal price;

    public SeatTypeInShowResponse(String seatTypeId, String seatTypeName, BigDecimal price) {
        this.seatTypeId = seatTypeId;
        this.seatTypeName = seatTypeName;
        this.price = price;
    }

    public static SeatTypeInShowResponse fromEntity(SeatTypeInShow seatTypeInShow) {
        return new SeatTypeInShowResponse(
                seatTypeInShow.getSeatType() != null ? seatTypeInShow.getSeatType().getId() : null,
                seatTypeInShow.getSeatType() != null ? seatTypeInShow.getSeatType().getLabel() : null,
                seatTypeInShow.getPrice()
        );
    }
}
