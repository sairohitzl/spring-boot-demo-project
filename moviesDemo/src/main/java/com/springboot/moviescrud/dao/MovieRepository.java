package com.springboot.moviescrud.dao;

import com.springboot.moviescrud.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
