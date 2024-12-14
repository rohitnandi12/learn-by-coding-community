package org.lbcc.bms.bms_monolith.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypeDto;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypeResponse;
import org.lbcc.bms.bms_monolith.admin.dto.seat.SeatTypesRequestDto;
import org.lbcc.bms.bms_monolith.admin.repository.SeatTypeRepository;
import org.lbcc.bms.bms_monolith.common.entity.SeatType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;

    SeatTypeService(SeatTypeRepository seatTypeRepository) {
        this.seatTypeRepository = seatTypeRepository;
    }

    public SeatTypeResponse createSeatType(SeatTypesRequestDto seatTypesRequestDto) {
        log.info("Creating seat type");
        List<SeatTypeDto> seatTypeDtos = seatTypesRequestDto.getSeatTypes();
        List<SeatType> seatTypes = seatTypeDtos.stream().map(SeatTypeDto::toEntity).toList();
        log.info("Saving seat types: {}", seatTypes);
        List<SeatType> savedSeatTypes = seatTypeRepository.saveAll(seatTypes);
        log.info("Seat types created successfully");
        return SeatTypeResponse.from(savedSeatTypes);
    }

    public List<SeatType> getSeatTypes(List<String> seatTypeIds) {
        List<UUID> uuidList = seatTypeIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        return seatTypeRepository.findAllById(uuidList);
    }
}
