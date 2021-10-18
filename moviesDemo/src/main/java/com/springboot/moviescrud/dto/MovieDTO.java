package com.springboot.moviescrud.dto;

import com.springboot.moviescrud.entity.Review;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class MovieDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private int length;
    private String genre;

    @ToString.Exclude
    private List<Review> reviews;
}
