package com.example.larica.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "link_delivery")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Column(nullable = false, length = 50)
    private String plataforma;

    @Column(name = "url_destino", nullable = false, columnDefinition = "TEXT")
    private String urlDestino;
}
