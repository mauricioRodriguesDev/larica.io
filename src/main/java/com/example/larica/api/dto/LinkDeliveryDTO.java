package com.example.larica.api.dto;

import lombok.Data;

@Data
public class LinkDeliveryDTO {
    private Long id; // Refatorado para Long
    private String plataforma;
    private String urlDestino;
}
