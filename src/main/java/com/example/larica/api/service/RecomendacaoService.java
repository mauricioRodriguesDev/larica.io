package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.PeriodoDia;
import com.example.larica.api.domain.Recomendacao;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.repository.RecomendacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    private final ClimaService climaService;
    private final RecomendacaoRepository recomendacaoRepository;

    public RecomendacaoService(ClimaService climaService, RecomendacaoRepository recomendacaoRepository) {
        this.climaService = climaService;
        this.recomendacaoRepository = recomendacaoRepository;
    }

    public List<Categoria> obterRecomendacoes() {
        String condicaoAtual = climaService.obterCondicaoClimaticaAtual()
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível obter a condição climática atual."));

        PeriodoDia periodoAtual = PeriodoDia.agora();

        List<Recomendacao> recomendacoes = recomendacaoRepository.findByClimaCondicaoAndPeriodoDia(condicaoAtual, periodoAtual);

        if (recomendacoes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma recomendação encontrada para o clima '" + condicaoAtual + "' e período '" + periodoAtual + "'.");
        }

        return recomendacoes.stream()
                .map(Recomendacao::getCategoria)
                .distinct()
                .collect(Collectors.toList());
    }
}
