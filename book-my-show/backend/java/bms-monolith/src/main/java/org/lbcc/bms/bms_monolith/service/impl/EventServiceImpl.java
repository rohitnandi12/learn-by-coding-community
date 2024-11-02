package org.lbcc.bms.bms_monolith.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.entity.Event;
import org.lbcc.bms.bms_monolith.exception.EventServiceException;
import org.lbcc.bms.bms_monolith.repository.IEventRepository;
import org.lbcc.bms.bms_monolith.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventServiceImpl implements IEventService {

    private final IEventRepository IEventRepository;

    @Autowired
    public EventServiceImpl(IEventRepository IEventRepository) {
        this.IEventRepository = IEventRepository;
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        try {
            Page<Event> eventsPage = IEventRepository.findAllWithDetails(pageable);
            log.info("Fetched {} events with pageable {}", eventsPage.getTotalElements(), pageable);

            return eventsPage;
        } catch (Exception e) {
            log.error("Error fetching events: {}", e.getMessage());
            throw new EventServiceException("Failed to fetch events", e);
        }
    }
}
