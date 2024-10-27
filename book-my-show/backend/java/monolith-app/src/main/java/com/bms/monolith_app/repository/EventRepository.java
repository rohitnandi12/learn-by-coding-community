package com.bms.monolith_app.repository;

import com.bms.monolith_app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByTitleContaining(String title);
    List<Event> findByEventType_Label(String type);
}
