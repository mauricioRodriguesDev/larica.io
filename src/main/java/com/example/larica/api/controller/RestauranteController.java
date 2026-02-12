package com.example.larica.api.controller;

import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurantes", description = "Endpoints para consultar restaurantes.")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os restaurantes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso.")
    })
    public ResponseEntity<List<RestauranteDTO>> listarTodos() {
        return ResponseEntity.ok(restauranteService.listarTodos());
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Lista todos os restaurantes de uma categoria específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhum restaurante encontrado para esta categoria.")
    })
    public ResponseEntity<List<RestauranteDTO>> listarPorCategoria(@PathVariable Integer categoriaId) {
        List<RestauranteDTO> restaurantes = restauranteService.listarPorCategoria(categoriaId);
        if (restaurantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(restaurantes);
    }
}
