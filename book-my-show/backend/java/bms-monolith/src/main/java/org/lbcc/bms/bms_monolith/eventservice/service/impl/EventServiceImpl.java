package org.lbcc.bms.bms_monolith.eventservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.admin.repository.SeatTypeRepository;
import org.lbcc.bms.bms_monolith.admin.service.AdminService;
import org.lbcc.bms.bms_monolith.admin.service.VenueService;
import org.lbcc.bms.bms_monolith.common.entity.*;
import org.lbcc.bms.bms_monolith.eventservice.dto.*;
import org.lbcc.bms.bms_monolith.common.entity.Event;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventResponse;
import org.lbcc.bms.bms_monolith.eventservice.exception.EventServiceException;
import org.lbcc.bms.bms_monolith.eventservice.helpers.EventHelpers;
import org.lbcc.bms.bms_monolith.eventservice.repository.EventTypeRepository;
import org.lbcc.bms.bms_monolith.eventservice.repository.IEventRepository;
import org.lbcc.bms.bms_monolith.eventservice.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements IEventService {

    private final IEventRepository IEventRepository;
    private final EventTypeRepository eventTypeRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final VenueService venueService;
    private final AdminService adminService;

    @Autowired
    public EventServiceImpl(IEventRepository IEventRepository,
                            EventTypeRepository eventTypeRepository,
                            SeatTypeRepository seatTypeRepository,
                            VenueService venueService,
                            AdminService adminService) {
        this.IEventRepository = IEventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.seatTypeRepository = seatTypeRepository;
        this.venueService = venueService;
        this.adminService = adminService;
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        try {
            Page<Event> eventsPage = IEventRepository.findAll(pageable);
            log.info("Fetched {} events with pageable {}", eventsPage.getTotalElements(), pageable);

            return eventsPage.map(EventResponse::fromEntity);
        } catch (Exception e) {
            log.error("Error fetching events: {}", e.getMessage());
            throw new EventServiceException("Failed to fetch events", e);
        }
    }

    @Override
    public EventResponse hostEvent(EventDTO request) {
        //TODO: add additional validations to check if timings are correct
        //Example: adding back dated shows should not be allowed
        // end date should be greater than start date
        Event event = mapEventDTOToEntity(request);

        Event savedEvent = IEventRepository.save(event); //save the event
        log.info("Event hosted successfully: {}", savedEvent);
        return EventResponse.fromEntity(savedEvent);
    }

    public Event mapEventDTOToEntity(EventDTO eventDTO) {
        Vendor vendor = adminService.findVendorById(UUID.fromString(eventDTO.getVendorId()));
        Venue venue = venueService.getVenueById(eventDTO.getVenueId());

        EventType eventType = eventTypeRepository.findById(UUID.fromString(eventDTO.getEventTypeId()))
                .orElse(null);

        Event event = Event.builder()
                .title(eventDTO.getTitle()).description(eventDTO.getDescription())
                .vendor(vendor).venue(venue).eventType(eventType)
                .startDate(eventDTO.getStartDate()).endDate(eventDTO.getEndDate())
                .build();

        addSeatsData(eventDTO, venue);

        List<EventShow> eventShows = eventDTO.getShows() != null ? eventDTO.getShows().stream()
                .map(EventHelpers::mapEventShowDTOToEntity)
                .toList() : List.of();

        eventShows.forEach(show -> show.setEvent(event));

        event.setShow(eventShows);

        return event;
    }


    @Override
    public EventResponse addShowsToEvent(EventDTO eventDTO) {
        Event event = IEventRepository.findById(UUID.fromString(eventDTO.getId()))
                .orElseThrow(() -> new EventServiceException("Event not found", new Throwable()));
        Venue venue = venueService.getVenueById(eventDTO.getVenueId());

        addSeatsData(eventDTO, venue);

        if (eventDTO.getShows() == null || eventDTO.getShows().isEmpty()) {
            throw new EventServiceException("No shows found in request", new Throwable());
        }

        List<EventShow> eventShows = eventDTO.getShows().stream()
                .map(EventHelpers::mapEventShowDTOToEntity)
                .toList();

        eventShows.forEach(show -> show.setEvent(event));
        event.setShow(event.getShow() == null ? new ArrayList<>() : event.getShow());
        event.getShow().addAll(eventShows);

        Event updatedEvent = IEventRepository.save(event);
        log.info("Shows added to event successfully: {}", updatedEvent);
        return EventResponse.fromEntity(updatedEvent);
    }

    @Override
    public EventTypeResponse addEventTypes(EventTypeRequestDto eventTypeRequestDto) {
        log.info("Adding event types: {}", eventTypeRequestDto.getEventTypes());
        List<String> eventTypesList = eventTypeRequestDto.getEventTypes();
        List<EventType> eventTypes = EventTypeRequestDto.toEntities(eventTypesList);
        List<EventType> savedEventTypes = eventTypeRepository.saveAll(eventTypes);
        log.info("Event types added successfully: {}", savedEventTypes);
        return new EventTypeResponse(EventTypeDto.fromEntities(savedEventTypes));
    }

    //Adds seats data (seatType and available seats) based on venue
    private void addSeatsData(EventDTO eventDTO, Venue venue) {
        eventDTO.getShows().forEach(show -> {
            show.setSeats(venue.getSeats());
            show.setSeatTypeMap(getSeatTypeMap());
        });
    }

    private Map<String, SeatType> getSeatTypeMap() {
        List<SeatType> seatTypes = seatTypeRepository.findAll(); //TODO: can be cached later
        return seatTypes.stream().collect(Collectors.toMap(x -> x.getId().toString(), x -> x));
    }
}
