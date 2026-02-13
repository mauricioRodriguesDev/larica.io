package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.Clima;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.repository.ClimaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecomendacaoServiceTest {

    @Mock
    private ClimaService climaService;

    @Mock
    private ClimaRepository climaRepository;

    @InjectMocks
    private RecomendacaoService recomendacaoService;

    @Test
    @DisplayName("Deve retornar categorias quando o clima é encontrado no banco")
    void obterRecomendacoes_CenarioFeliz() {
        // Arrange
        String condicaoClimatica = "Rain";
        Categoria sopa = Categoria.builder().id(1).nome("Sopas e Caldos").build();
        Categoria massa = Categoria.builder().id(2).nome("Massas").build();
        Clima climaChuvoso = Clima.builder()
                .id(1)
                .condicao(condicaoClimatica)
                .categorias(Set.of(sopa, massa))
                .build();

        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.of(condicaoClimatica));
        when(climaRepository.findByCondicao(condicaoClimatica)).thenReturn(Optional.of(climaChuvoso));

        // Act
        List<Categoria> recomendacoes = recomendacaoService.obterRecomendacoes();

        // Assert
        assertNotNull(recomendacoes);
        assertEquals(2, recomendacoes.size());
        assertTrue(recomendacoes.contains(sopa));
        assertTrue(recomendacoes.contains(massa));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando a API de clima falha")
    void obterRecomendacoes_QuandoClimaServiceFalha() {
        // Arrange
        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            recomendacaoService.obterRecomendacoes();
        });

        String expectedMessage = "Não foi possível obter a condição climática atual.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o clima não está cadastrado no banco")
    void obterRecomendacoes_QuandoClimaNaoEncontradoNoBanco() {
        // Arrange
        String condicaoClimaticaNaoCadastrada = "Fog";
        when(climaService.obterCondicaoClimaticaAtual()).thenReturn(Optional.of(condicaoClimaticaNaoCadastrada));
        when(climaRepository.findByCondicao(condicaoClimaticaNaoCadastrada)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            recomendacaoService.obterRecomendacoes();
        });

        String expectedMessage = "Nenhuma categoria encontrada para a condição climática: " + condicaoClimaticaNaoCadastrada;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
