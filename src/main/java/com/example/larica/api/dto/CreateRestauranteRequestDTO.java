package com.example.larica.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateRestauranteRequestDTO {

    @NotBlank(message = "O nome não pode ser vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "O ID da categoria é obrigatório.")
    @Positive(message = "O ID da categoria deve ser um número positivo.")
    private Integer categoriaId;

    private String endereco;

    private BigDecimal rating;
}
