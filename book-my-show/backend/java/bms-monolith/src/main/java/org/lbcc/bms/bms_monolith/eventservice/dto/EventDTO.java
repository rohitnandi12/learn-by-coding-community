package org.lbcc.bms.bms_monolith.eventservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class EventDTO {

    private String id;

    @Size(min = 10, max = 50, message = "Title must be between 10 and 50 characters.")
    private String title;

    @Size(max = 100, message = "Description must be less than 100 characters.")
    private String description;

    //vendor details
    @NotNull(message = "Vendor id is required.")
    private String vendorId;

    //venue details
    @NotNull(message = "Venue id is required.")
    private String venueId;

    //available shows
    private List<EventShowDTO> shows;

    //event type details
    @NotNull(message = "Event type id is required.")
    private String eventTypeId;

    @NotNull(message = "Start date is required.")
    private Instant startDate;

    @NotNull(message = "End date is required.")
    private Instant endDate;

}
