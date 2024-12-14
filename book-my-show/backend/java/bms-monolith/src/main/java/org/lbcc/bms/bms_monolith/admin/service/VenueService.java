package org.lbcc.bms.bms_monolith.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.admin.controller.SeatTypeService;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatDto;
import org.lbcc.bms.bms_monolith.admin.dto.venue.VenueDto;
import org.lbcc.bms.bms_monolith.admin.dto.venue.VenueOnboardResponse;
import org.lbcc.bms.bms_monolith.admin.repository.VenueRepository;
import org.lbcc.bms.bms_monolith.common.entity.Seat;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.lbcc.bms.bms_monolith.common.entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenueService {

    private final VenueRepository venueRepository;
    private final SeatTypeService seatTypeService;

    @Autowired
    public VenueService(VenueRepository venueRepository, SeatTypeService seatTypeService) {
        this.venueRepository = venueRepository;
        this.seatTypeService = seatTypeService;
    }

    public VenueOnboardResponse createVenue(VenueDto venueDto) {
        List<String> seatTypeIds = venueDto.getSeats().stream().map(SeatDto::getSeatTypeId).toList();
        List<SeatType> seatTypes = seatTypeService.getSeatTypes(seatTypeIds);
        Map<String, SeatType> seatTypeMap = seatTypes.stream()
                .collect(Collectors.toMap(seatType -> seatType.getId().toString(), seatType -> seatType));
        Venue venue = VenueDto.toEntity(venueDto, seatTypeMap);
        log.info("Creating venue: {}", venue);
        venueRepository.save(venue);
        log.info("Venue created successfully: {}", venue);
        return new VenueOnboardResponse(venue.getId().toString(), venue.getName());
    }

    public Venue getVenueById(String venueId) {
        if (venueId == null) {
            throw new RuntimeException("Venue id cannot be null");
        }
        return venueRepository.findById(UUID.fromString(venueId)).orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public Venue addSeatsToVenue(String venueId, List<SeatDto> seatDtos) {
        Venue venue = getVenueById(venueId);
        List<SeatType> seatTypes = seatTypeService.getSeatTypes(seatDtos.stream().map(SeatDto::getSeatTypeId).toList());
        Map<String, SeatType> seatTypeMap = seatTypes.stream()
                .collect(Collectors.toMap(seatType -> seatType.getId().toString(), seatType -> seatType));
        List<Seat> addedSeats = seatDtos.stream().map(seatDto -> SeatDto.toEntity(seatDto, venue, seatTypeMap)).toList();
        venue.getSeats().addAll(addedSeats);

        if (venue.getSeats().size() > 100) {
            throw new RuntimeException("Cannot add more than 100 seats");
        }

        return venueRepository.save(venue);
    }
}
