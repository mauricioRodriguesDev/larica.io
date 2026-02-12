package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.Clima;
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
        Optional<String> condicaoAtualOpt = climaService.obterCondicaoClimaticaAtual();

        if (condicaoAtualOpt.isEmpty()) {
            // Se a API de clima falhar, podemos retornar uma lista padrão ou vazia
            return Collections.emptyList();
        }

        String condicaoAtual = condicaoAtualOpt.get();
        Optional<Clima> climaOpt = climaRepository.findByCondicao(condicaoAtual);

        return climaOpt.map(clima -> List.copyOf(clima.getCategorias()))
                .orElse(Collections.emptyList());
    }
}
