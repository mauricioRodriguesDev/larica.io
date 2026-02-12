package com.example.larica.api.controller;

import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.service.RestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listarTodos() {
        return ResponseEntity.ok(restauranteService.listarTodos());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<RestauranteDTO>> listarPorCategoria(@PathVariable Integer categoriaId) {
        List<RestauranteDTO> restaurantes = restauranteService.listarPorCategoria(categoriaId);
        if (restaurantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(restaurantes);
    }
}
