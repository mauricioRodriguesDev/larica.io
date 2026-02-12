package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.Restaurante;
import com.example.larica.api.dto.CreateRestauranteRequestDTO;
import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.mapper.RestauranteMapper;
import com.example.larica.api.repository.CategoriaRepository;
import com.example.larica.api.repository.RestauranteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CategoriaRepository categoriaRepository;
    private final RestauranteMapper restauranteMapper;

    public RestauranteService(RestauranteRepository restauranteRepository, CategoriaRepository categoriaRepository, RestauranteMapper restauranteMapper) {
        this.restauranteRepository = restauranteRepository;
        this.categoriaRepository = categoriaRepository;
        this.restauranteMapper = restauranteMapper;
    }

    @Transactional
    public RestauranteDTO criarRestaurante(CreateRestauranteRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com ID " + dto.getCategoriaId() + " não encontrada."));

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.getNome());
        restaurante.setCategoria(categoria);
        restaurante.setEndereco(dto.getEndereco());
        restaurante.setRating(dto.getRating());
        restaurante.setAtivo(true);

        Restaurante novoRestaurante = restauranteRepository.save(restaurante);
        return restauranteMapper.toDTO(novoRestaurante);
    }

    @Transactional(readOnly = true)
    public Page<RestauranteDTO> listarTodos(Pageable pageable) {
        return restauranteRepository.findAll(pageable)
                .map(restauranteMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<RestauranteDTO> listarPorCategoria(Integer categoriaId) {
        return restauranteRepository.findByCategoriaId(categoriaId).stream()
                .map(restauranteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
