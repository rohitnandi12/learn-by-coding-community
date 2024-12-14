package org.lbcc.bms.bms_monolith.eventservice.dto;

import lombok.Builder;
import lombok.Getter;
import org.lbcc.bms.bms_monolith.common.entity.Event;

import java.util.List;

@Getter
@Builder
public class EventResponse {

    private String id;
    private String title;
    private String description;
    private String vendorName;
    private String venueName;
    private List<EventShowResponse> shows;
    private String eventTypeName;
    private String startDate;
    private String endDate;
    private String thumbnailUrl;

    public static EventResponse fromEntity(Event event) {
        return new EventResponse(
                event.getId().toString(),
                event.getTitle(),
                event.getDescription(),
                event.getVendor() != null ? event.getVendor().getName() : null,
                event.getVenue() != null ? event.getVenue().getName() : null,
                event.getShow() != null ? event.getShow().stream()
                        .map(EventShowResponse::fromEntity)
                        .toList() : null,
                event.getEventType() != null ? event.getEventType().getLabel() : null,
                event.getStartDate() != null ? event.getStartDate().toString() : null,
                event.getEndDate() != null ? event.getEndDate().toString() : null,
                event.getThumbnailUrl()
        );
    }
}


