package com.example.larica.api.controller;

import com.example.larica.api.service.ClimaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clima")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping("/condicao-atual")
    public ResponseEntity<String> getCondicaoClimaticaAtual() {
        Optional<String> condicao = climaService.obterCondicaoClimaticaAtual();
        return condicao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(500).body("Não foi possível obter a condição climática."));
    }
}
