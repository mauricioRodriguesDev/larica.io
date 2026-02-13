package com.example.larica.api.repository;

import com.example.larica.api.domain.Clima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClimaRepository extends JpaRepository<Clima, Long> { // Refatorado para Long
    Optional<Clima> findByCondicao(String condicao);
}
