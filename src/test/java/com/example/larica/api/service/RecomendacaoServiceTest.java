package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.PeriodoDia;
import com.example.larica.api.domain.Recomendacao;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.repository.RecomendacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecomendacaoServiceTest {

    @Mock
    private ClimaService climaService;

    @Mock
    private RecomendacaoRepository recomendacaoRepository;

    @InjectMocks
    private RecomendacaoService recomendacaoService;

    @Test
    @DisplayName("Deve retornar categorias quando a recomendação é encontrada")
    void obterRecomendacoes_CenarioFeliz() {
        // Arrange
        String condicaoClimatica = "Rain";
        PeriodoDia periodo = PeriodoDia.NOITE;
        Categoria sopa = Categoria.builder().id(1).nome("Sopas e Caldos").build();
        Recomendacao recomendacao = new Recomendacao(1L, sopa, null, periodo);

        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.of(condicaoClimatica));
        when(recomendacaoRepository.findByClimaCondicaoAndPeriodoDia(condicaoClimatica, periodo)).thenReturn(List.of(recomendacao));

        // Mock estático para controlar o PeriodoDia.agora()
        try (MockedStatic<PeriodoDia> periodoDiaMockedStatic = Mockito.mockStatic(PeriodoDia.class)) {
            periodoDiaMockedStatic.when(PeriodoDia::agora).thenReturn(periodo);

            // Act
            List<Categoria> recomendacoes = recomendacaoService.obterRecomendacoes();

            // Assert
            assertNotNull(recomendacoes);
            assertEquals(1, recomendacoes.size());
            assertEquals("Sopas e Caldos", recomendacoes.get(0).getNome());
        }
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando a API de clima falha")
    void obterRecomendacoes_QuandoClimaServiceFalha() {
        // Arrange
        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            recomendacaoService.obterRecomendacoes();
        });
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando nenhuma recomendação é encontrada")
    void obterRecomendacoes_QuandoNenhumaRecomendacaoEncontrada() {
        // Arrange
        String condicaoClimatica = "Clear";
        PeriodoDia periodo = PeriodoDia.MANHA;
        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.of(condicaoClimatica));
        when(recomendacaoRepository.findByClimaCondicaoAndPeriodoDia(condicaoClimatica, periodo)).thenReturn(Collections.emptyList());

        try (MockedStatic<PeriodoDia> periodoDiaMockedStatic = Mockito.mockStatic(PeriodoDia.class)) {
            periodoDiaMockedStatic.when(PeriodoDia::agora).thenReturn(periodo);

            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> {
                recomendacaoService.obterRecomendacoes();
            });
        }
    }
}
