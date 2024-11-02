package org.lbcc.bms.bms_monolith.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lbcc.bms.bms_monolith.common.response.ApiListResponse;
import org.lbcc.bms.bms_monolith.entity.Event;
import org.lbcc.bms.bms_monolith.exception.EventServiceException;
import org.lbcc.bms.bms_monolith.service.IEventService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.when;

public class EventControllerTest {

    @Mock
    private IEventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventsSuccess() {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setTitle("Sample Event");

        Page<Event> eventPage = new PageImpl<>(List.of(event), PageRequest.of(0, 10), 1);

        when(eventService.getAllEvents(any(Pageable.class))).thenReturn(eventPage);

        ResponseEntity<ApiListResponse<Event>> response = eventController.getAllEvents(PageRequest.of(0, 10));

        ApiListResponse<Event> expectedResponse = ApiListResponse.<Event>builder()
                .success(true)
                .message("Events fetched successfully")
                .data(eventPage.getContent())
                .totalItems((int) eventPage.getTotalElements())
                .totalPages(eventPage.getTotalPages())
                .currentPage(eventPage.getNumber())
                .pageSize(eventPage.getSize())
                .hasNextPage(eventPage.hasNext())
                .hasPreviousPage(eventPage.hasPrevious())
                .build();

        // Assertions to verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.getMessage(), response.getBody().getMessage());
        assertEquals(expectedResponse.isSuccess(), response.getBody().isSuccess());
        assertEquals(1, response.getBody().getTotalItems());
    }

    @Test
    void testGetAllEventsInvalidPageParameters() {
        Page<Event> emptyPage = Page.empty(PageRequest.of(0, 10));

        when(eventService.getAllEvents(PageRequest.of(0, 10))).thenReturn(emptyPage);

        ResponseEntity<ApiListResponse<Event>> response = eventController.getAllEvents(PageRequest.of(0, 10));

        ApiListResponse<Event> expectedResponse = ApiListResponse.<Event>builder()
                .success(false)
                .message("Invalid page or size parameters")
                .data(emptyPage.getContent())
                .totalItems((int) emptyPage.getTotalElements())
                .totalPages(emptyPage.getTotalPages())
                .currentPage(0)
                .pageSize(emptyPage.getSize())
                .hasNextPage(emptyPage.hasNext())
                .hasPreviousPage(emptyPage.hasPrevious())
                .build();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Events fetched successfully", response.getBody().getMessage());
        assertEquals(true, response.getBody().isSuccess());
    }

    @Test
    void testGetAllEventsExceptionHandling() {
        when(eventService.getAllEvents(any(Pageable.class)))
                .thenThrow(new EventServiceException("Failed to fetch events", new RuntimeException()));

        EventServiceException exception = assertThrows(EventServiceException.class, () -> {
            eventController.getAllEvents(PageRequest.of(0, 10));
        });

        assertEquals("Failed to fetch events", exception.getMessage());
    }

}
