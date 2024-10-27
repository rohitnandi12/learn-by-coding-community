package com.bms.monolith_app.service;

import com.bms.monolith_app.dto.EventDetailsDto;
import com.bms.monolith_app.entity.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {

    public List<Event> findEvents(String title, String type);

    public EventDetailsDto getEventDetails(UUID eventId);
}
