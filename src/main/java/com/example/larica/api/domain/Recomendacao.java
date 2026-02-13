package com.example.larica.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recomendacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clima_id")
    private Clima clima;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodo_dia", nullable = false)
    private PeriodoDia periodoDia;
}
