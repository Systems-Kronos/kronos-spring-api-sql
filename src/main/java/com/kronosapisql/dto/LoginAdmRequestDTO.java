package com.kronosapisql.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAdmRequestDTO {
    @NotNull(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    private String senha;

}
