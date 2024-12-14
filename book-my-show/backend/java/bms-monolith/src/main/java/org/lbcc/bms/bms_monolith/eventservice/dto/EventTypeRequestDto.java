package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.entity.EventType;

import java.util.List;

@Data
public class EventTypeRequestDto {
    private List<String> eventTypes;

    public static List<EventType> toEntities(List<String> eventTypes) {
        return eventTypes.stream().map(EventType::new).toList();
    }
}
