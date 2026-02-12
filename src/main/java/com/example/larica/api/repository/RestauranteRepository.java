package com.example.larica.api.repository;

import com.example.larica.api.domain.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    List<Restaurante> findByCategoriaId(Integer categoriaId);
}
