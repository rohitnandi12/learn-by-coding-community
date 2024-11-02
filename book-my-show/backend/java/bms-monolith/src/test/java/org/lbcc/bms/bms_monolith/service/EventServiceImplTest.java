package org.lbcc.bms.bms_monolith.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lbcc.bms.bms_monolith.entity.Event;
import org.lbcc.bms.bms_monolith.exception.EventServiceException;
import org.lbcc.bms.bms_monolith.repository.IEventRepository;
import org.lbcc.bms.bms_monolith.service.impl.EventServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EventServiceImplTest {

    @Mock
    private IEventRepository IEventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventsSuccess() {
        Event event = new Event();
        event.setTitle("Sample Event");
        event.setDescription("Sample Description");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Event> eventPage = new PageImpl<>(Collections.singletonList(event), pageable, 1);

        when(IEventRepository.findAllWithDetails(pageable)).thenReturn(eventPage);

        Page<Event> response = eventService.getAllEvents(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals("Sample Event", response.getContent().get(0).getTitle());
        assertEquals("Sample Description", response.getContent().get(0).getDescription());
    }

    @Test
    void testGetAllEventsExceptionHandling() {
        when(IEventRepository.findAllWithDetails(any(Pageable.class))).thenThrow(new RuntimeException("Database error"));

        EventServiceException exception = assertThrows(EventServiceException.class, () -> {
            eventService.getAllEvents(PageRequest.of(0, 10));
        });

        assertEquals("Failed to fetch events", exception.getMessage());
    }
}
