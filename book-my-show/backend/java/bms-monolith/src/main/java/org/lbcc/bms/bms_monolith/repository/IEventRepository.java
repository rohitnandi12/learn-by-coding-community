package org.lbcc.bms.bms_monolith.repository;

import org.lbcc.bms.bms_monolith.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e JOIN FETCH e.vendor JOIN FETCH e.venue JOIN FETCH e.eventType ORDER BY e.startDate DESC")
    Page<Event> findAllWithDetails(Pageable pageable);
}