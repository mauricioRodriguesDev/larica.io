package com.example.larica.api.controller;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.dto.CreateRestauranteRequestDTO;
import com.example.larica.api.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/test-data/clean-db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Deve retornar 201 Created ao criar um restaurante com dados válidos")
    void criarRestaurante_ComDadosValidos_Retorna201() throws Exception {
        // Arrange
        Categoria categoria = categoriaRepository.save(Categoria.builder().nome("Categoria para Teste").build());
        CreateRestauranteRequestDTO novoRestauranteDTO = new CreateRestauranteRequestDTO();
        novoRestauranteDTO.setNome("Restaurante Teste");
        novoRestauranteDTO.setCategoriaId(categoria.getId());
        novoRestauranteDTO.setEndereco("Rua de Teste, 123");
        novoRestauranteDTO.setRating(new BigDecimal("4.5"));

        String jsonBody = objectMapper.writeValueAsString(novoRestauranteDTO);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Restaurante Teste"));
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request ao tentar criar um restaurante com nome em branco")
    void criarRestaurante_ComNomeEmBranco_Retorna400() throws Exception {
        // Arrange
        Categoria categoria = categoriaRepository.save(Categoria.builder().nome("Categoria para Teste 2").build());
        CreateRestauranteRequestDTO novoRestauranteDTO = new CreateRestauranteRequestDTO();
        novoRestauranteDTO.setNome("");
        novoRestauranteDTO.setCategoriaId(categoria.getId());

        String jsonBody = objectMapper.writeValueAsString(novoRestauranteDTO);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", containsString("nome: O nome não pode ser vazio.")));
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao tentar criar um restaurante com categoria inexistente")
    void criarRestaurante_ComCategoriaInexistente_Retorna404() throws Exception {
        // Arrange
        CreateRestauranteRequestDTO novoRestauranteDTO = new CreateRestauranteRequestDTO();
        novoRestauranteDTO.setNome("Restaurante Fantasma");
        novoRestauranteDTO.setCategoriaId(999L);

        String jsonBody = objectMapper.writeValueAsString(novoRestauranteDTO);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
