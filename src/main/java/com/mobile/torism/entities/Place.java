package com.mobile.torism.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "places")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 1000)
    private String description;

    private String location;

    private boolean voted;

    private int upvotes;

    private int downvotes;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Comment> comments;
}