package com.example.larica.api.controller;

import com.example.larica.api.dto.CreateRestauranteRequestDTO;
import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.dto.UpdateRestauranteDTO;
import com.example.larica.api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurantes", description = "Endpoints para consultar e gerenciar restaurantes.")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo restaurante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada.")
    })
    public ResponseEntity<RestauranteDTO> criarRestaurante(@Valid @RequestBody CreateRestauranteRequestDTO dto) {
        RestauranteDTO novoRestaurante = restauranteService.criarRestaurante(dto);
        return new ResponseEntity<>(novoRestaurante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um restaurante existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos."),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado.")
    })
    public ResponseEntity<RestauranteDTO> atualizarRestaurante(@PathVariable Long id, @Valid @RequestBody UpdateRestauranteDTO dto) { // Refatorado para Long
        RestauranteDTO restauranteAtualizado = restauranteService.atualizarRestaurante(id, dto);
        return ResponseEntity.ok(restauranteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um restaurante existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurante deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado.")
    })
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) { // Refatorado para Long
        restauranteService.deletarRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lista todos os restaurantes de forma paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso.")
    })
    public ResponseEntity<Page<RestauranteDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(restauranteService.listarTodos(pageable));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Lista todos os restaurantes de uma categoria específica de forma paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso.")
    })
    public ResponseEntity<Page<RestauranteDTO>> listarPorCategoria(@PathVariable Long categoriaId, Pageable pageable) { // Refatorado para Long
        return ResponseEntity.ok(restauranteService.listarPorCategoria(categoriaId, pageable));
    }
}
