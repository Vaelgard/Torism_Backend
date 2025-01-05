package com.mobile.torism.repositories;

import com.mobile.torism.entities.Favorites;
import com.mobile.torism.entities.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
    List<Favorites> findFavoritesByUser(OurUsers user);
}
