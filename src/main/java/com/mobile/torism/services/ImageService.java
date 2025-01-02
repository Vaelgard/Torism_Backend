package com.mobile.torism.services;


import com.mobile.torism.entities.Image;
import com.mobile.torism.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    public Image uploadImage(MultipartFile imageFile) {
        try {
            // Logic for uploading the image (e.g., to Cloudinary)
            String imageUrl = cloudinaryService.upload(imageFile).toString();
            Image image = new Image();
            image.setImageUrl(imageUrl);
            // Save the image entity if necessary
            imageRepository.save(image);
            return image;
        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
    }
}