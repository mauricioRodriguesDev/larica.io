package com.example.larica.api.service;

import com.example.larica.api.domain.Restaurante;
import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.mapper.RestauranteMapper;
import com.example.larica.api.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    public RestauranteService(RestauranteRepository restauranteRepository, RestauranteMapper restauranteMapper) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteMapper = restauranteMapper;
    }

    @Transactional(readOnly = true)
    public List<RestauranteDTO> listarTodos() {
        return restauranteRepository.findAll().stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RestauranteDTO> listarPorCategoria(Integer categoriaId) {
        return restauranteRepository.findByCategoriaId(categoriaId).stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
