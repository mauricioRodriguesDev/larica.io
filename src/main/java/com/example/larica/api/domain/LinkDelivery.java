package com.example.larica.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "link_delivery")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LinkDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Column(nullable = false, length = 50)
    private String plataforma;

    @Column(name = "url_destino", nullable = false, columnDefinition = "TEXT")
    private String urlDestino;
}
