package org.lbcc.bms.bms_monolith.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiErrorResponse {
    @Builder.Default
    private boolean success = false;
    private String message;
    private String code;
    @Builder.Default
    private Instant timestamp = Instant.now();
}
