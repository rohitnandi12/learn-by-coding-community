package org.lbcc.bms.bms_monolith.admin.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.entity.Seat;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.lbcc.bms.bms_monolith.common.entity.Venue;
import org.lbcc.bms.bms_monolith.common.enums.OperationalStatus;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
public class SeatDto {

    private String seatTypeId;
    private String label; // seat number like A1, B22, etc.

    public static Seat toEntity(SeatDto seatDto, Venue venue, Map<String, SeatType> seatTypeMap) {
        SeatType seatType = seatTypeMap.get(seatDto.getSeatTypeId());
        return new Seat(seatType, venue, OperationalStatus.OPERATIONAL, seatDto.getLabel());
    }

    public static List<Seat> seatDtoListToSeatList(List<SeatDto> seatDtoList, Venue venue, Map<String, SeatType> seatTypeMap) {
        return seatDtoList.stream().map(seatDto -> toEntity(seatDto, venue, seatTypeMap)).toList();
    }

}
