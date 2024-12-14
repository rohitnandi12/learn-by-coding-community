package org.lbcc.bms.bms_monolith.admin.dto.seat;

import lombok.Data;

import java.util.List;

@Data
public class SeatTypesRequestDto {

    private List<SeatTypeDto> seatTypes;

}