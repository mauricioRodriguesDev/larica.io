package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.Clima;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.repository.ClimaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecomendacaoService {

    private final ClimaService climaService;
    private final ClimaRepository climaRepository;

    public RecomendacaoService(ClimaService climaService, ClimaRepository climaRepository) {
        this.climaService = climaService;
        this.climaRepository = climaRepository;
    }

    public List<Categoria> obterRecomendacoes() {
        String condicaoAtual = climaService.obterCondicaoClimaticaAtual()
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível obter a condição climática atual."));

        Clima clima = climaRepository.findByCondicao(condicaoAtual)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma categoria encontrada para a condição climática: " + condicaoAtual));

        return List.copyOf(clima.getCategorias());
    }
}
