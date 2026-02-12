package com.example.larica.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "clima")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String condicao;

    @Column(name = "descricao_amigavel", length = 100)
    private String descricaoAmigavel;

    @ManyToMany(mappedBy = "climas")
    private Set<Categoria> categorias;
}
