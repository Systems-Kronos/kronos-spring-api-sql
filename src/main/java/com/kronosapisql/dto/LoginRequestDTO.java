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
public class LoginRequestDTO {
    @NotNull(message = "O cpf é obrigatório")
    private String cpf;

    @NotNull(message = "A senha é obrigatória")
    private String senha;
}
