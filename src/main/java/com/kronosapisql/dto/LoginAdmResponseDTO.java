package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAdmResponseDTO {
    private Long Administracaoid;
    private String token;

}
