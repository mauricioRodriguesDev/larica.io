package com.example.larica.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RestauranteDTO {
    private Integer id;
    private String nome;
    private String endereco;
    private BigDecimal rating;
    private Integer categoriaId;
    private List<LinkDeliveryDTO> links;
}
