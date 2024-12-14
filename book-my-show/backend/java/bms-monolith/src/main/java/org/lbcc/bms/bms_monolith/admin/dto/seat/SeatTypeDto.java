package org.lbcc.bms.bms_monolith.admin.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class SeatTypeDto {

    private String label;
    private BigDecimal price;

    public static SeatType toEntity(SeatTypeDto seatTypeDto) {
        return new SeatType(seatTypeDto.getLabel(), seatTypeDto.getPrice());
    }
}
