package org.lbcc.bms.bms_monolith.admin.controller;

import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypeResponse;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypesRequestDto;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.lbcc.bms.bms_monolith.common.response.ApiListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seat-types")
public class SeatTypeController {

    private final SeatTypeService seatTypeService;

    public SeatTypeController(SeatTypeService seatTypeService) {
        this.seatTypeService = seatTypeService;
    }

    @PostMapping
    public ResponseEntity<ApiListResponse<SeatTypeResponse>> createSeatType(@RequestBody SeatTypesRequestDto seatTypesRequestDto) {
        SeatTypeResponse response = seatTypeService.createSeatType(seatTypesRequestDto);
        ApiListResponse<SeatTypeResponse> apiResponse = ApiListResponse.<SeatTypeResponse>builder()
                .success(true).message("Seat types created successfully")
                .data(response.getSeatTypeIds().stream()
                        .map(id -> new SeatTypeResponse(List.of(id)))
                        .collect(Collectors.toList())).build();
        return ResponseEntity.ok(apiResponse);
    }
}
