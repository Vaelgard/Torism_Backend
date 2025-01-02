package com.mobile.torism.dto;

import com.mobile.torism.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private Integer id;
    private String name;
    private String description;
    private String location;
    private Image image;
}
