package com.example.larica.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clima")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Refatorado para Long

    @Column(nullable = false, unique = true, length = 50)
    private String condicao;

    @Column(name = "descricao_amigavel", length = 100)
    private String descricaoAmigavel;

    @OneToMany(mappedBy = "clima", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Recomendacao> recomendacoes = new HashSet<>();
}
