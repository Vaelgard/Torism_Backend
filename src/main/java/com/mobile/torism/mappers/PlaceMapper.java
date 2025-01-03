package com.mobile.torism.mappers;

import com.mobile.torism.dto.PlaceDTO;
import com.mobile.torism.entities.Image;
import com.mobile.torism.entities.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mapping(source = "image.imageUrl", target = "image") // Map imageUrl to the image field in DTO
    PlaceDTO toDTO(Place place);

    @Mapping(source = "image", target = "image", qualifiedByName = "mapImageUrlToImage")
    Place toEntity(PlaceDTO placeDTO);

    List<PlaceDTO> toDTOList(List<Place> places);

    @Named("mapImageUrlToImage")
    default Image mapImageUrlToImage(String imageUrl) {
        return imageUrl == null ? null : new Image(null, imageUrl, null);
    }
}
