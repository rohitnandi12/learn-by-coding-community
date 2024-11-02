package org.lbcc.bms.bms_monolith.controller;

import org.lbcc.bms.bms_monolith.common.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.common.response.ApiListResponse;
import org.lbcc.bms.bms_monolith.entity.Event;
import org.lbcc.bms.bms_monolith.service.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/events")
public class EventController {

    private final IEventService iEventService;

    public EventController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @GetMapping
    public ResponseEntity<ApiListResponse<Event>> getAllEvents(
            @PageableDefault(page = 0, size = 10)
            @SortDefault(sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Event> eventsPage = iEventService.getAllEvents(pageable);

        ApiListResponse<Event> response = ApiListResponse.<Event>builder()
                .success(true)
                .message(BMSConstants.EVENT_SUCCESS_MESSAGE)
                .setPage(eventsPage)
                .build();

        return ResponseEntity.ok(response);
    }

}
