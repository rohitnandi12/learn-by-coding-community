package org.lbcc.bms.bms_monolith.service.helpers;

import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypeInShowDTO;
import org.lbcc.bms.bms_monolith.common.entity.Seat;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.lbcc.bms.bms_monolith.common.entity.Venue;
import org.lbcc.bms.bms_monolith.common.enums.Genre;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventDTO;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventShowDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class EventHelpers {

    public static final String EVENT_TITLE = "Circus Festival 2024";
    public static final String EVENT_DESCRIPTION = "A two-day music festival featuring top artists.";

    //List of all seat type IDs(Regular, VIP, GOLD)
    private static final String SEAT_TYPE_ID1 = "2d72c22e-951d-42fc-b570-a5c99d6a7fc0";
    private static final String SEAT_TYPE_ID2 = "7fe67850-56a9-45ae-a0e4-c04cda82b895";
    private static final String SEAT_TYPE_ID3 = "1ad50f91-fcf1-4d80-b54e-fada5b6ad973";

    // Constants for SeatType
    private static final String REGULAR_LABEL = "Regular";
    private static final String VIP_LABEL = "VIP";
    private static final String GOLD_LABEL = "GOLD";


    public static List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        List<SeatType> seatTypes = createSeatTypes();

        String[] seatLabels = {"A1", "A2", "B1", "B2"};
        for (int i = 0; i < seatLabels.length; i++) {
            seats.add(createSeat(seatLabels[i], seatTypes.get(i % seatTypes.size())));
        }
        return seats;
    }

    private static Seat createSeat(String label, SeatType seatType) {
        return Seat.builder()
                .id(UUID.randomUUID()).label(label)
                .seatType(seatType).build();
    }

    private static SeatTypeInShowDTO createSeatTypeInShowDTO(String seatTypeId, BigDecimal price) {
        return SeatTypeInShowDTO.builder()
                .seatTypeId(seatTypeId)
                .price(price)
                .build();
    }

    private static EventShowDTO createEventShowDTO(Instant showDate, Instant startTime, Instant endTime,
                                                   List<Genre> genres, List<SeatTypeInShowDTO> seatTypeInShows) {
        return EventShowDTO.builder()
                .showDate(showDate)
                .startTime(startTime)
                .endTime(endTime)
                .genres(genres)
                .seatTypeInShows(seatTypeInShows)
                .build();
    }

    public static EventDTO createEventDTO() {
        // Create seat types for the shows
        SeatTypeInShowDTO seatType1Show1 = createSeatTypeInShowDTO(SEAT_TYPE_ID1, new BigDecimal("150.00"));
        SeatTypeInShowDTO seatType2Show1 = createSeatTypeInShowDTO(SEAT_TYPE_ID2, new BigDecimal("250.00"));
        SeatTypeInShowDTO seatType1Show2 = createSeatTypeInShowDTO(SEAT_TYPE_ID2, new BigDecimal("300.00"));

        // Create EventShowDTOs
        EventShowDTO show1 = createEventShowDTO(
                Instant.parse("2024-06-15T00:00:00.000Z"),
                Instant.parse("2024-06-15T10:00:00.000Z"),
                Instant.parse("2024-06-15T22:00:00.000Z"),
                Arrays.asList(Genre.COMEDY, Genre.DRAMA),
                Arrays.asList(seatType1Show1, seatType2Show1)
        );

        EventShowDTO show2 = createEventShowDTO(
                Instant.parse("2024-06-16T00:00:00.000Z"),
                Instant.parse("2024-06-16T10:00:00.000Z"),
                Instant.parse("2024-06-16T22:00:00.000Z"),
                Arrays.asList(Genre.ACTION, Genre.THRILLER),
                List.of(seatType1Show2)
        );

        // Create the main EventDTO
        return EventDTO.builder()
                .title("Circus Festival 2024")
                .description("A two-day music festival featuring top artists.")
                .vendorId("79984c71-6080-4251-adcc-254c8939a8bc")
                .venueId("8d05ee6c-2f69-4650-b5a5-0aa7ebfd9909")
                .eventTypeId("2172e49a-ba32-4e52-9814-6d6ed043c6ee")
                .startDate(Instant.parse("2024-06-15T00:00:00.000Z"))
                .endDate(Instant.parse("2024-06-16T23:59:59.000Z"))
                .shows(Arrays.asList(show1, show2))
                .build();
    }

    private static SeatType createSeatType(String uuid, String label, BigDecimal price) {
        SeatType seatType = new SeatType();
        seatType.setId(UUID.fromString(uuid));
        seatType.setLabel(label);
        seatType.setPrice(price);
        return seatType;
    }

    public static List<SeatType> createSeatTypes() {
        return List.of(
                createSeatType(SEAT_TYPE_ID1, REGULAR_LABEL, BigDecimal.valueOf(100)),
                createSeatType(SEAT_TYPE_ID2, VIP_LABEL, BigDecimal.valueOf(200)),
                createSeatType(SEAT_TYPE_ID3, GOLD_LABEL, BigDecimal.valueOf(300))
        );
    }

    public static Venue createVenue(EventDTO eventDTO) {
        Venue venue = new Venue();
        venue.setId(UUID.fromString(eventDTO.getVenueId()));
        List<Seat> seats = EventHelpers.createSeats();
        venue.setSeats(seats);

        return venue;
    }
}
