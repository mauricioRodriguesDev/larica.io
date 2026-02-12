package com.example.larica.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "clima")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "categorias")
@ToString(exclude = "categorias")
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String condicao;

    @Column(name = "descricao_amigavel", length = 100)
    private String descricaoAmigavel;

    @ManyToMany(mappedBy = "climas")
    @JsonBackReference
    private Set<Categoria> categorias;
}
