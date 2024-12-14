package org.lbcc.bms.bms_monolith.admin.dto.seat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class SeatTypeInShowDTO {

    @NotNull(message = "Seat type ID is required.")
    private String seatTypeId; // ID of the seat type

    @NotNull(message = "label/type name is required.")
    private String typeName; // E.g., Regular, VIP

    private BigDecimal price; // Price for this seat type
}
