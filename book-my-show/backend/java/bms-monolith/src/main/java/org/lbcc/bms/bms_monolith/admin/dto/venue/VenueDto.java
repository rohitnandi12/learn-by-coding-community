package org.lbcc.bms.bms_monolith.admin.dto.venue;

import lombok.Data;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatDto;
import org.lbcc.bms.bms_monolith.common.dto.AddressDto;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.lbcc.bms.bms_monolith.common.entity.Venue;
import org.lbcc.bms.bms_monolith.common.enums.OperationalStatus;
import org.lbcc.bms.bms_monolith.common.enums.VenueType;

import java.util.List;
import java.util.Map;

@Data
public class VenueDto {
    private String id;
    private String name;
    private AddressDto address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private Integer totalSeatingCapacity;
    private VenueType venueType;
    private List<SeatDto> seats;

    public static Venue toEntity(VenueDto venueDto, Map<String, SeatType> seatTypeMap) {
        Venue venue = new Venue();
        venue.setName(venueDto.getName());
        venue.setAddress(AddressDto.addressDtoToAddress(venueDto.getAddress()));
        venue.setTotalSeatingCapacity(venueDto.getTotalSeatingCapacity());
        venue.setOperationalStatus(OperationalStatus.OPERATIONAL);
        venue.setVenueType(venueDto.getVenueType());
        venue.setSeats(SeatDto.seatDtoListToSeatList(venueDto.getSeats(), venue ,seatTypeMap));
        return venue;
    }
}
