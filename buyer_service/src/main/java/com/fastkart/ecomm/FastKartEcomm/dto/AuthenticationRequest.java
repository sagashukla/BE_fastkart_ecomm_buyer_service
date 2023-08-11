package com.fastkart.ecomm.FastKartEcomm.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
