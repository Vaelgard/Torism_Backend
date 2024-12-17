package com.mobile.torism.mappers;

import com.mobile.torism.dto.PlaceDTO;
import com.mobile.torism.entities.Place;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    PlaceDTO toDTO(Place place);
    Place toEntity(PlaceDTO placeDTO);
    List<PlaceDTO> toDTOList(List<Place> places);
} 