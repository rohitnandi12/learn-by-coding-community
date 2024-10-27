package com.bms.monolith_app.dto;

import com.bms.monolith_app.entity.Event;
import com.bms.monolith_app.entity.EventShow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsDto {
    private Event event;
    private List<EventShow> shows;
}
