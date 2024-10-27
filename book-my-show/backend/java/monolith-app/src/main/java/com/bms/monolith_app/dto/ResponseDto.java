package com.bms.monolith_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String statusMsg;

}
