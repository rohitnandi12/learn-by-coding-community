package org.lbcc.bms.bms_monolith.eventservice.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.common.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.common.response.ApiListResponse;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventDTO;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventResponse;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventTypeRequestDto;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventTypeResponse;
import org.lbcc.bms.bms_monolith.eventservice.service.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/events")
@Slf4j
public class EventController {

    private final IEventService iEventService;

    public EventController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @GetMapping
    public ResponseEntity<ApiListResponse<EventResponse>> getAllEvents(
            @PageableDefault(size = BMSConstants.DEFAULT_PAGE_SIZE)
            @SortDefault(sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<EventResponse> eventsPage = iEventService.getAllEvents(pageable);

        ApiListResponse<EventResponse> response = ApiListResponse.<EventResponse>builder()
                .success(true)
                .message(BMSConstants.EVENT_SUCCESS_MESSAGE)
                .setPage(eventsPage)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/event-types")
    public ResponseEntity<?> addEventTypes(@RequestBody EventTypeRequestDto eventTypeRequestDto) {
        EventTypeResponse eventTypeResponse = iEventService.addEventTypes(eventTypeRequestDto);
        ApiListResponse<EventTypeResponse> response = ApiListResponse.<EventTypeResponse>builder()
                .success(true)
                .message("event types added successfully")
                .data(List.of(eventTypeResponse))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/host")
    public ResponseEntity<?>  hostEvent(@RequestBody @Valid EventDTO event) {
        EventResponse hostedEvent = iEventService.hostEvent(event);
        log.info("Event hosted successfully");
        ApiResponse<EventResponse> response = ApiResponse.<EventResponse>builder()
                .success(true)
                .message("event hosted successfully")
                .data(hostedEvent)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/shows") //adding more shows to an event
    public ResponseEntity<?>  addShows(@RequestBody @Valid EventDTO event) {
        EventResponse hostedEvent = iEventService.addShowsToEvent(event);
        ApiResponse<EventResponse> response = ApiResponse.<EventResponse>builder()
                .success(true)
                .message("Shows added successfully to event")
                .data(hostedEvent)
                .build();
        return ResponseEntity.ok(response);
    }

}
