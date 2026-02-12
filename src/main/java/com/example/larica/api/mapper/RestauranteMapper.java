package com.example.larica.api.mapper;

import com.example.larica.api.domain.Restaurante;
import com.example.larica.api.dto.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {LinkDeliveryMapper.class}) // Adicionado o LinkDeliveryMapper
public interface RestauranteMapper {

    RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "links", target = "links") // Mapeamento explícito para clareza
    RestauranteDTO toDTO(Restaurante restaurante);

    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(target = "links", ignore = true) // Ignoramos na conversão para entidade por enquanto
    Restaurante toEntity(RestauranteDTO restauranteDTO);
}
