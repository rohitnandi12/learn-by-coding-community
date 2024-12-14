package org.lbcc.bms.bms_monolith.admin.dto.venue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VenueOnboardResponse {
    private String venueId;
    private String venueName;
}
