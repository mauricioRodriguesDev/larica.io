package com.example.larica.api.repository;

import com.example.larica.api.domain.PeriodoDia;
import com.example.larica.api.domain.Recomendacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {

    @Query("SELECT r FROM Recomendacao r JOIN FETCH r.categoria WHERE r.clima.condicao = :condicao AND r.periodoDia = :periodo")
    List<Recomendacao> findByClimaCondicaoAndPeriodoDia(String condicao, PeriodoDia periodo);
}
