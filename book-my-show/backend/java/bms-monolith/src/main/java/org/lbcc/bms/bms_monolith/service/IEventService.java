package org.lbcc.bms.bms_monolith.service;

import org.lbcc.bms.bms_monolith.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {

    Page<Event> getAllEvents(Pageable pageable);
}
