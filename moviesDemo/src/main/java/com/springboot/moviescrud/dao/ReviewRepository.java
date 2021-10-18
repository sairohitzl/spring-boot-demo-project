package com.springboot.moviescrud.dao;


import com.springboot.moviescrud.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
