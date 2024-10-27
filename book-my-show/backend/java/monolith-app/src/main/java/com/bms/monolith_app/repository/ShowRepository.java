package com.bms.monolith_app.repository;

import com.bms.monolith_app.entity.Event;
import com.bms.monolith_app.entity.EventShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<EventShow, UUID> {
    List<EventShow> findByEvent(Event event);
}