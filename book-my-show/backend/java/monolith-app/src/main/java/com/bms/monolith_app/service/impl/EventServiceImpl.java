package com.bms.monolith_app.service.impl;

import com.bms.monolith_app.dto.EventDetailsDto;
import com.bms.monolith_app.entity.Event;
import com.bms.monolith_app.entity.EventShow;
import com.bms.monolith_app.exception.ResourceNotFoundException;
import com.bms.monolith_app.repository.EventRepository;
import com.bms.monolith_app.repository.ShowRepository;
import com.bms.monolith_app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Event> findEvents(String title, String type) {
        if (title != null) {
            return eventRepository.findByTitleContaining(title);
        }
        if (type != null) {
            return eventRepository.findByEventType_Label(type);
        }
        return eventRepository.findAll();
    }

    public EventDetailsDto getEventDetails(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        List<EventShow> shows = showRepository.findByEvent(event);

        return new EventDetailsDto(event, shows);
    }

}
