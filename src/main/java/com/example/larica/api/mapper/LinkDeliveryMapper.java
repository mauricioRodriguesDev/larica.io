package com.example.larica.api.mapper;

import com.example.larica.api.domain.LinkDelivery;
import com.example.larica.api.dto.LinkDeliveryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LinkDeliveryMapper {

    LinkDeliveryMapper INSTANCE = Mappers.getMapper(LinkDeliveryMapper.class);

    LinkDeliveryDTO toDTO(LinkDelivery linkDelivery);

    LinkDelivery toEntity(LinkDeliveryDTO linkDeliveryDTO);
}
