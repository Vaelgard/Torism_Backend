package com.mobile.torism.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.torism.dto.PlaceDTO;
import com.mobile.torism.entities.Image;
import com.mobile.torism.mappers.PlaceMapper;
import com.mobile.torism.services.ImageService;
import com.mobile.torism.services.PlaceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final ObjectMapper objectMapper;
    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(
            @RequestParam("place") String placeData,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            PlaceDTO placeDTO = objectMapper.readValue(placeData, PlaceDTO.class);
            PlaceDTO createdPlace = placeService.createPlace(placeDTO, imageFile);

            System.out.println("Place created: " + createdPlace.getImage());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PlaceDTO> updatePlace(
            @PathVariable Integer id,
            @RequestParam("place") String placeData,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            PlaceDTO placeDTO = objectMapper.readValue(placeData, PlaceDTO.class);
            PlaceDTO updatedPlace = placeService.updatePlace(id, placeDTO, imageFile);
            return ResponseEntity.ok(updatedPlace);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePlace(@PathVariable Integer id) {
        try {
            placeService.deletePlace(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlace(@PathVariable Integer id) {
        try {
            PlaceDTO place = placeService.getPlace(id);
            return ResponseEntity.ok(place);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> getAllPlaces() {
        try {
            List<PlaceDTO> places = placeService.getAllPlaces();
            if (places.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(places);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlaceDTO>> searchPlaces(@RequestParam String name) {
        try {
            List<PlaceDTO> places = placeService.searchPlaces(name);
            if (places.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(places);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/upvote/{id}")
    public ResponseEntity<PlaceDTO> upvotePlaces(@RequestParam Integer id) {
         return ResponseEntity.ok(placeService.upvote(id));
    }
    @GetMapping("downvote/{id}")
    public ResponseEntity<PlaceDTO> downvotePlaces(@RequestParam Integer id) {
        return ResponseEntity.ok(placeService.downvote(id));
    }
    @PostMapping("/favorite")
    public ResponseEntity<String> favoritePlace(
            @RequestParam Integer placeId,
            @RequestParam String userEmail) {
        try {
            placeService.createFavPlace(placeId, userEmail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/favorite/{userEmail}")
    public ResponseEntity<List<PlaceDTO>> favoritePlaces(@PathVariable String userEmail) {
        List<PlaceDTO> list=placeService.getAllFavPlaces(userEmail);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(list);
        }
    }
}
