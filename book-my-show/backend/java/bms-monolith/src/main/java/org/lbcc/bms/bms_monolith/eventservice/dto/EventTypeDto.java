package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.entity.EventType;

import java.util.List;

@Data
@AllArgsConstructor
public class EventTypeDto {
    private String id;
    private String label;

    public static EventTypeDto fromEntity(EventType eventType) {
        return new EventTypeDto(
                eventType.getId().toString(),
                eventType.getLabel()
        );
    }

    public static List<EventTypeDto> fromEntities(List<EventType> eventTypes) {
        return eventTypes.stream().map(EventTypeDto::fromEntity).toList();
    }

}
