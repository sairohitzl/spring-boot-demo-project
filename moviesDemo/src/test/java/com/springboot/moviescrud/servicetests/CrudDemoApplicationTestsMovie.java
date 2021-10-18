package com.springboot.moviescrud.servicetests;

import com.springboot.moviescrud.dao.MovieRepository;
import com.springboot.moviescrud.entity.Movie;
import com.springboot.moviescrud.service.MovieService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CrudDemoApplicationTestsMovie {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void findAllTest() {
        when(movieRepository.findAll()).thenReturn(Stream.of(new Movie("movie1", 50, "genre1"),
                new Movie("movie1", 50, "genre1")).collect(Collectors.toList()));

        assertEquals(2, movieService.findAll().size());
    }

    @Test
    public void findByIdTest() {
        int id = 0;
        Movie movie = new Movie("movie1", 50, "genre1");
        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        assertEquals(movie, movieService.findById(0));

    }

    @Test
    public void save()
    {
        Movie movie = new Movie("movie2", 50, "genre2");
        movieService.save(movie);
        verify(movieRepository,times(1)).save(movie);
    }

    @Test
    public void deleteByIdTest(){
        Movie movie = new Movie("movie2", 50, "genre2");
        movieService.deleteById(0);
        verify(movieRepository,times(1)).deleteById(0);
    }

}
