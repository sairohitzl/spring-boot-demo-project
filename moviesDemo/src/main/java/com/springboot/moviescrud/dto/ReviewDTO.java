package com.springboot.moviescrud.dto;

import com.springboot.moviescrud.entity.Movie;
import com.springboot.moviescrud.entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class ReviewDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reviewContent;


    private Movie movie;

    private User user;



}
