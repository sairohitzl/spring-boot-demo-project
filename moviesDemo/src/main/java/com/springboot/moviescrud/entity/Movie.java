package com.springboot.moviescrud.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movie")
public class Movie {

    // define fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int id;

   // @NotNull(message = "movie's name cannot be empty!.")
    //@Size(min = 1,max = 50)
    @Column(name="movie_name")
    private String movieName;

    //@Positive(message = "value should be greater than 0")
   // @NotNull(message = "this field cannot be empty")
    @Column(name="length")
    private int length;


    @Column(name="genre")
    private String genre;


    // define constructors

    @OneToMany(mappedBy = "movie",
            cascade = CascadeType.ALL )        // reviews get deleted when movie gets deleted
    private List<Review> reviews;

    public Movie() {

    }

    public Movie(String movieName, int length, String genre) {
        this.movieName = movieName;
        this.length = length;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void add(Review review)
    {
        if(reviews==null)
        {
            reviews = new ArrayList<>();
        }

        reviews.add(review);
        review.setMovie(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", length=" + length +
                ", genre='" + genre + '\'' +
                '}';
    }
}