package com.fastkart.ecomm.FastKartEcomm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastkart.ecomm.FastKartEcomm.dto.TokenValidationResponse;
import org.springframework.http.ResponseEntity;

public class UtilsConvertDto {
    public static TokenValidationResponse convertToDto(ResponseEntity<String> response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TokenValidationResponse dto = mapper.readValue(response.getBody(), TokenValidationResponse.class);
        return dto;
    }
}
