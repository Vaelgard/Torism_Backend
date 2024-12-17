package com.mobile.torism.repositories;

import com.mobile.torism.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findByNameContainingIgnoreCase(String name);
} 