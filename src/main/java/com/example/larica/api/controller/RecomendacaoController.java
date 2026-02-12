package com.example.larica.api.controller;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.service.RecomendacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recomendacoes")
public class RecomendacaoController {

    private final RecomendacaoService recomendacaoService;

    public RecomendacaoController(RecomendacaoService recomendacaoService) {
        this.recomendacaoService = recomendacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getRecomendacoes() {
        List<Categoria> recomendacoes = recomendacaoService.obterRecomendacoes();
        if (recomendacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recomendacoes);
    }
}
