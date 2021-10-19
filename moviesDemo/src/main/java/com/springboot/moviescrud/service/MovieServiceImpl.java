package com.springboot.moviescrud.service;

import com.springboot.moviescrud.dao.MovieRepository;
import com.springboot.moviescrud.entity.Movie;
import com.springboot.moviescrud.exceptions.MyRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;



    @Autowired
    public MovieServiceImpl( MovieRepository movieRepository)
    {
        this.movieRepository=movieRepository;
    }
    @Override
    public List<Movie> findAll() {

        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int theId) {
        Optional<Movie> result = movieRepository.findById(theId);
        Movie theMovie=null;
        if(result.isPresent()){
            theMovie = result.get();
        }
        else
        {
            throw new MyRunTimeException("Did not  find movie of id: "+theId);
        }
        return theMovie;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteById(int theId) {
        movieRepository.deleteById(theId);
    }

    @Override
    public Page<Movie> findPaginated(int pageNo, int pageSize,
                                        String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return this.movieRepository.findAll(pageable);
    }
}