package com.example.larica.api.mapper;

import com.example.larica.api.domain.Restaurante;
import com.example.larica.api.dto.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    @Mapping(source = "categoria.id", target = "categoriaId")
    RestauranteDTO toDTO(Restaurante restaurante);

    @Mapping(source = "categoriaId", target = "categoria.id")
    Restaurante toEntity(RestauranteDTO restauranteDTO);
}
