package org.lbcc.bms.bms_monolith.eventservice.service;

import org.lbcc.bms.bms_monolith.common.entity.Event;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventDTO;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventResponse;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventTypeRequestDto;
import org.lbcc.bms.bms_monolith.eventservice.dto.EventTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {

    Page<EventResponse>  getAllEvents(Pageable pageable);

    EventResponse hostEvent(EventDTO eventDTO);

    EventTypeResponse addEventTypes(EventTypeRequestDto eventTypeRequestDto);

    EventResponse addShowsToEvent(EventDTO eventDTO);
}
