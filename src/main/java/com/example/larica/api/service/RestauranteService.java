package com.example.larica.api.service;

import com.example.larica.api.domain.Categoria;
import com.example.larica.api.domain.Restaurante;
import com.example.larica.api.dto.CreateRestauranteRequestDTO;
import com.example.larica.api.dto.RestauranteDTO;
import com.example.larica.api.dto.UpdateRestauranteDTO;
import com.example.larica.api.exception.ResourceNotFoundException;
import com.example.larica.api.mapper.RestauranteMapper;
import com.example.larica.api.repository.CategoriaRepository;
import com.example.larica.api.repository.RestauranteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public RestauranteDTO atualizarRestaurante(Long id, UpdateRestauranteDTO dto) { // Refatorado para Long
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante com ID " + id + " não encontrado."));

        updateRestauranteData(restaurante, dto);

        Restaurante restauranteAtualizado = restauranteRepository.save(restaurante);
        return restauranteMapper.toDTO(restauranteAtualizado);
    }

    @Transactional
    public void deletarRestaurante(Long id) { // Refatorado para Long
        if (!restauranteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurante com ID " + id + " não encontrado.");
        }
        restauranteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<RestauranteDTO> listarTodos(Pageable pageable) {
        return restauranteRepository.findAll(pageable)
                .map(restauranteMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<RestauranteDTO> listarPorCategoria(Long categoriaId, Pageable pageable) { // Refatorado para Long
        return restauranteRepository.findByCategoriaId(categoriaId, pageable)
                .map(restauranteMapper::toDTO);
    }

    private void updateRestauranteData(Restaurante restaurante, UpdateRestauranteDTO dto) {
        if (dto.getNome() != null) {
            restaurante.setNome(dto.getNome());
        }
        if (dto.getEndereco() != null) {
            restaurante.setEndereco(dto.getEndereco());
        }
        if (dto.getRating() != null) {
            restaurante.setRating(dto.getRating());
        }
    }
}
