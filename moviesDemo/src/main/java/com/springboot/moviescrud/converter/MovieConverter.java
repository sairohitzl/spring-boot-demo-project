package com.springboot.moviescrud.converter;

import com.springboot.moviescrud.dto.MovieDTO;
import com.springboot.moviescrud.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter
{
    public MovieDTO entityToDto(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setMovieName(movie.getMovieName());
        movieDTO.setLength(movie.getLength());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setReviews(movie.getReviews());


        return movieDTO;

    }

    public Movie dtoToEntity(MovieDTO movieDTO)
    {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setMovieName(movieDTO.getMovieName());
        movie.setLength(movieDTO.getLength());
        movie.setGenre(movieDTO.getGenre());
        movie.setReviews(movieDTO.getReviews());
        return movie;
    }

    public List<MovieDTO> entityToDto(List<Movie> movies)
    {
        return  movies.stream().map(this::entityToDto).collect(Collectors.toList());

    }
    public List<Movie> dtoToEntity(List<MovieDTO> movieDTOList){

        return movieDTOList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
