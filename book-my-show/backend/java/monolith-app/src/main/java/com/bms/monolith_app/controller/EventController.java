package com.bms.monolith_app.controller;

import com.bms.monolith_app.dto.EventDetailsDto;
import com.bms.monolith_app.entity.Event;
import com.bms.monolith_app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String type) {
        return eventService.findEvents(title, type);
    }

    @GetMapping("/{eventId}")
    public EventDetailsDto getEventDetails(@PathVariable UUID eventId) {
        return eventService.getEventDetails(eventId);
    }
}
