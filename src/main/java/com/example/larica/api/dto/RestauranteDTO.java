package com.example.larica.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RestauranteDTO {
    private Long id; // Refatorado para Long
    private String nome;
    private String endereco;
    private BigDecimal rating;
    private Long categoriaId; // Refatorado para Long
    private List<LinkDeliveryDTO> links;
}
