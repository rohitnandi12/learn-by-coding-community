package org.lbcc.bms.bms_monolith.eventservice.repository;

import org.lbcc.bms.bms_monolith.common.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {

    EventType findByLabel(String label);
    List<EventType> findByIdIn(List<UUID> ids);
}
