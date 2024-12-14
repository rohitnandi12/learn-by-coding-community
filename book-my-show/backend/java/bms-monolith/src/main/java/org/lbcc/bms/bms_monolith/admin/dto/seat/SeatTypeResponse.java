package org.lbcc.bms.bms_monolith.admin.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;

import java.util.List;

@AllArgsConstructor
@Getter
public class SeatTypeResponse {

    private List<String> seatTypeIds;

    public static SeatTypeResponse from(List<SeatType> seatTypes) {
        return new SeatTypeResponse(seatTypes.stream().map(x -> x.getId().toString()).toList());
    }
}
