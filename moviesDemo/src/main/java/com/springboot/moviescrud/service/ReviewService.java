package com.springboot.moviescrud.service;

import com.springboot.moviescrud.entity.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> findAll();

    public Review findById(int theId);

    public void save(Review review);

    public void deleteById(int theId);

}