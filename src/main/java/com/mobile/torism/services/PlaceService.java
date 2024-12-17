package com.mobile.torism.services;

import com.mobile.torism.dto.PlaceDTO;
import com.mobile.torism.entities.Place;
import com.mobile.torism.mappers.PlaceMapper;
import com.mobile.torism.repositories.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        Place place = placeMapper.toEntity(placeDTO);
        Place savedPlace = placeRepository.save(place);
        return placeMapper.toDTO(savedPlace);
    }

    public PlaceDTO updatePlace(Integer id, PlaceDTO placeDTO) {
        Place place = placeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Place not found"));
        
        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLocation(placeDTO.getLocation());
        
        Place updatedPlace = placeRepository.save(place);
        return placeMapper.toDTO(updatedPlace);
    }

    public void deletePlace(Integer id) {
        if (!placeRepository.existsById(id)) {
            throw new EntityNotFoundException("Place not found");
        }
        placeRepository.deleteById(id);
    }

    public PlaceDTO getPlace(Integer id) {
        Place place = placeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Place not found"));
        return placeMapper.toDTO(place);
    }

    public List<PlaceDTO> getAllPlaces() {
        return placeMapper.toDTOList(placeRepository.findAll());
    }

    public List<PlaceDTO> searchPlaces(String name) {
        List<Place> places = placeRepository.findByNameContainingIgnoreCase(name);
        return placeMapper.toDTOList(places);
    }
} 