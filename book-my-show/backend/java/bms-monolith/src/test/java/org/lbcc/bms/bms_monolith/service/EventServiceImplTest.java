package org.lbcc.bms.bms_monolith.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbcc.bms.bms_monolith.admin.repository.SeatTypeRepository;
import org.lbcc.bms.bms_monolith.admin.service.AdminService;
import org.lbcc.bms.bms_monolith.admin.service.VenueService;
import org.lbcc.bms.bms_monolith.common.entity.*;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventDTO;
import org.lbcc.bms.bms_monolith.common.entity.Event;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventResponse;
import org.lbcc.bms.bms_monolith.eventservice.exception.EventServiceException;
import org.lbcc.bms.bms_monolith.eventservice.repository.EventTypeRepository;
import org.lbcc.bms.bms_monolith.eventservice.repository.IEventRepository;
import org.lbcc.bms.bms_monolith.eventservice.service.impl.EventServiceImpl;
import org.lbcc.bms.bms_monolith.service.helpers.EventHelpers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private IEventRepository IEventRepository;

    @Mock
    private EventTypeRepository eventTypeRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private AdminService adminService;

    @Mock
    private VenueService venueService;

    @Mock
    private SeatTypeRepository seatTypeRepository;


    @Test
    void testGetAllEventsSuccess() {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setTitle("Sample Event");
        event.setDescription("Sample Description");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Event> eventPage = new PageImpl<>(Collections.singletonList(event), pageable, 1);

        when(IEventRepository.findAll(pageable)).thenReturn(eventPage);

        Page<EventResponse> response = eventService.getAllEvents(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals("Sample Event", response.getContent().get(0).getTitle());
        assertEquals("Sample Description", response.getContent().get(0).getDescription());
    }

    @Test
    void testGetAllEventsExceptionHandling() {
        when(IEventRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Database error"));

        Pageable pageable = PageRequest.of(0, 10);
        EventServiceException exception = assertThrows(EventServiceException.class, () ->
            eventService.getAllEvents(pageable)
        );

        assertEquals("Failed to fetch events", exception.getMessage());
    }

    @Test
    void mapEventDTOToEntitySuccess() {
        EventDTO eventDTO = EventHelpers.createEventDTO();

        Vendor vendor = new Vendor();
        vendor.setId(UUID.fromString(eventDTO.getVendorId()));

        Venue venue = EventHelpers.createVenue(eventDTO);
        List<SeatType> seatTypes = EventHelpers.createSeatTypes();

        EventType eventType = new EventType();
        eventType.setId(UUID.fromString(eventDTO.getEventTypeId()));

        when(adminService.findVendorById(UUID.fromString(eventDTO.getVendorId()))).thenReturn(vendor);
        when(venueService.getVenueById(String.valueOf(UUID.fromString(eventDTO.getVenueId())))).thenReturn(venue);
        when(eventTypeRepository.findById(UUID.fromString(eventDTO.getEventTypeId()))).thenReturn(Optional.of(eventType));
        when(seatTypeRepository.findAll()).thenReturn(seatTypes);

        Event event = eventService.mapEventDTOToEntity(eventDTO);

        assertNotNull(event);
        assertEquals(EventHelpers.EVENT_TITLE, event.getTitle());
        assertEquals(EventHelpers.EVENT_DESCRIPTION, event.getDescription());
        assertEquals(vendor, event.getVendor());
        assertEquals(venue, event.getVenue());
        assertEquals(eventType, event.getEventType());
        assertEquals(venue.getSeats().size(), event.getVenue().getSeats().size());
        assertEquals(event.getShow().size(), eventDTO.getShows().size());
    }

    @Test
    void mapEventDTOToEntityThrowsExceptionWhenVendorNotFound() {
        EventDTO eventDTO = EventHelpers.createEventDTO();

        when(adminService.findVendorById(UUID.fromString(eventDTO.getVendorId()))).thenThrow(new IllegalArgumentException("Vendor not found"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.mapEventDTOToEntity(eventDTO);
        });

        assertEquals("Vendor not found", exception.getMessage());
    }

    @Test
    void mapEventDTOToEntityThrowsExceptionWhenVenueNotFound() {
        EventDTO eventDTO = EventHelpers.createEventDTO();

        Vendor vendor = new Vendor();
        vendor.setId(UUID.fromString(eventDTO.getVendorId()));

        when(adminService.findVendorById(UUID.fromString(eventDTO.getVendorId()))).thenReturn(vendor);
        when(venueService.getVenueById(String.valueOf(UUID.fromString(eventDTO.getVenueId())))).thenThrow(new IllegalArgumentException("Invalid venue ID"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.mapEventDTOToEntity(eventDTO);
        });

        assertEquals("Invalid venue ID", exception.getMessage());
    }

}
