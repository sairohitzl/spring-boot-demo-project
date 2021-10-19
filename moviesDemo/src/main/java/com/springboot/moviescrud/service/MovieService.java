package com.springboot.moviescrud.service;

import com.springboot.moviescrud.entity.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {

    public List<Movie> findAll();

    public Movie findById(int theId);

    public void save(Movie movie);

    public void deleteById(int theId);

    Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}