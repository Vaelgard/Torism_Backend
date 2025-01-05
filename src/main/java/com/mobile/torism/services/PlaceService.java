package com.mobile.torism.services;

import com.mobile.torism.dto.PlaceDTO;
import com.mobile.torism.entities.Favorites;
import com.mobile.torism.entities.Image;
import com.mobile.torism.entities.OurUsers;
import com.mobile.torism.entities.Place;
import com.mobile.torism.mappers.PlaceMapper;
import com.mobile.torism.repositories.FavoritesRepository;
import com.mobile.torism.repositories.PlaceRepository;
import com.mobile.torism.repositories.UsersRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    private final CloudinaryService cloudinaryService;
    private final FavoritesRepository favoritesRepository;
    private final UsersRepo usersRepo;

    public PlaceDTO createPlace(PlaceDTO placeDTO, MultipartFile imageFile) throws IOException {
        Place place = placeMapper.toEntity(placeDTO);

        // Handle image upload via Cloudinary
        if (imageFile != null && !imageFile.isEmpty()) {
            Map uploadResult = cloudinaryService.upload(imageFile);
            String imageUrl = (String) uploadResult.get("url");
            String imageId = (String) uploadResult.get("public_id");
            Image image = new Image(imageFile.getOriginalFilename(), imageUrl, imageId);

            place.setImage(image); // Set the image to the place
        }

        Place savedPlace = placeRepository.save(place);
        return placeMapper.toDTO(savedPlace);
    }

    public PlaceDTO updatePlace(Integer id, PlaceDTO placeDTO, MultipartFile imageFile) throws IOException {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLocation(placeDTO.getLocation());

        // Handle image update
        if (imageFile != null && !imageFile.isEmpty()) {
            // Delete existing image from Cloudinary
            if (place.getImage() != null) {
                cloudinaryService.delete(place.getImage().getImageId());
            }

            // Upload new image
            Map uploadResult = cloudinaryService.upload(imageFile);
            String imageUrl = (String) uploadResult.get("url");
            String imageId = (String) uploadResult.get("public_id");
            Image newImage = new Image(imageFile.getOriginalFilename(), imageUrl, imageId);

            place.setImage(newImage); // Set the new image to the place
        }

        Place updatedPlace = placeRepository.save(place);
        return placeMapper.toDTO(updatedPlace);
    }

    public void deletePlace(Integer id) throws IOException {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        // Delete associated image from Cloudinary
        if (place.getImage() != null) {
            cloudinaryService.delete(place.getImage().getImageId());
        }

        placeRepository.delete(place);
    }

    public PlaceDTO getPlace(Integer id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));
        return placeMapper.toDTO(place);
    }

    public List<PlaceDTO> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return placeMapper.toDTOList(places);
    }

    public List<PlaceDTO> searchPlaces(String name) {
        List<Place> places = placeRepository.findByNameContainingIgnoreCase(name);
        return placeMapper.toDTOList(places);
    }
    public PlaceDTO upvote(Integer id) {
        Place place=placeRepository.findPlaceById(id);
        place.setUpvotes(place.getUpvotes()+1);
        place.setVoted(true);
        return placeMapper.toDTO(place);
    }
    public PlaceDTO downvote(Integer id) {
        Place place=placeRepository.findPlaceById(id);
        place.setDownvotes(place.getDownvotes()-1);
        place.setVoted(true);
        return placeMapper.toDTO(place);
    }
    public void createFavPlace(Integer placeId, String userEmail) throws IOException {
        Place place = placeRepository.findPlaceById(placeId);
        OurUsers user=usersRepo.findOurUsersByEmail(userEmail);
        Favorites favorites= new Favorites(null,user,place);
        favoritesRepository.save(favorites);
    }
    public List<PlaceDTO> getAllFavPlaces(String userEmail) {
        // Find the user by email
        OurUsers user = usersRepo.findOurUsersByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + userEmail);
        }
        List<Favorites> favorites = favoritesRepository.findFavoritesByUser(user);
        List<Place> favoritePlaces = favorites.stream()
                .map(Favorites::getPlace)
                .toList();
        return placeMapper.toDTOList(favoritePlaces);
    }


}
