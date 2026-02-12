package com.example.larica.api.controller;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.service.RecomendacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recomendacoes")
@Tag(name = "Recomendações", description = "Endpoint principal para obter sugestões de comida.")
public class RecomendacaoController {

    private final RecomendacaoService recomendacaoService;

    public RecomendacaoController(RecomendacaoService recomendacaoService) {
        this.recomendacaoService = recomendacaoService;
    }

    @GetMapping
    @Operation(summary = "Obtém recomendações de categorias de comida com base no clima atual do Rio de Janeiro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias recomendadas retornada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhuma categoria encontrada para a condição climática atual ou falha na API de clima."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public ResponseEntity<List<Categoria>> getRecomendacoes() {
        List<Categoria> recomendacoes = recomendacaoService.obterRecomendacoes();
        if (recomendacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recomendacoes);
    }
}
