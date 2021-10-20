package com.springboot.moviescrud.controller;

import com.springboot.moviescrud.converter.MovieConverter;
import com.springboot.moviescrud.converter.ReviewConverter;
import com.springboot.moviescrud.converter.UserConverter;
import com.springboot.moviescrud.dto.MovieDTO;
import com.springboot.moviescrud.dto.ReviewDTO;
import com.springboot.moviescrud.dto.UserDTO;
import com.springboot.moviescrud.entity.Authority;
import com.springboot.moviescrud.entity.Movie;
import com.springboot.moviescrud.entity.Review;
import com.springboot.moviescrud.entity.User;
import com.springboot.moviescrud.service.MovieService;
import com.springboot.moviescrud.service.ReviewService;
import com.springboot.moviescrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


//@RestController
@Controller
@RequestMapping("/api")       //parent request mapping
public class MovieRestController {

    private static final String REDIRECT_ADDRESS = "redirect:/api/movies-directory";
    private static final String REVIEW_MODEL_NAME = "reviews";
    private static final String MOVIE_MODEL_NAME  = "movie";
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;
    public MovieRestController(){}

    @Autowired
    public MovieRestController(MovieService theMovieService) {
        movieService=theMovieService;
    }


    @GetMapping("/movies-directory")
    public String paginatedMovies(Model model)
    {
        return findPaginated(1,"movieName","asc",model);
    }

    @GetMapping("/movie-reviews")
    public String movieReviews(@RequestParam("movieId") int theMovieId,Model model)
    {
        MovieConverter movieConverter = new MovieConverter();
        ReviewConverter reviewConverter = new ReviewConverter();
        MovieDTO movieDTO = movieConverter.entityToDto(movieService.findById(theMovieId));
        List<Review> reviews = movieDTO.getReviews();
        List<ReviewDTO> reviewDTOList =  reviewConverter.entityToDto(reviews);
        model.addAttribute(REVIEW_MODEL_NAME,reviewDTOList);


        return "list-reviews";
    }

    @GetMapping("/user-reviews")
    public String userReviews(Model model)
    {
        UserConverter userConverter = new UserConverter();
        ReviewConverter reviewConverter = new ReviewConverter();
        String name = SecurityContextHolder.getContext().getAuthentication().getName(); //**
        UserDTO userDTO = userConverter.entityToDto(userService.findByUsername(name));
        List<ReviewDTO> reviewDTOList = reviewConverter.entityToDto(userDTO.getReviews());


        model.addAttribute(REVIEW_MODEL_NAME,reviewDTOList);


        return "user-reviews";
    }



    @GetMapping("/add-movie")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data

        MovieDTO movieDTO = new MovieDTO();

        theModel.addAttribute(MOVIE_MODEL_NAME, movieDTO);

        return "movie-form";
    }

    @GetMapping("/update-movie")
    public String showFormForUpdate(@RequestParam("movieId") int id, Model model)
    {   MovieConverter movieConverter = new MovieConverter();
        MovieDTO movieDTO  = movieConverter.entityToDto(movieService.findById(id));

        model.addAttribute(MOVIE_MODEL_NAME,movieDTO);
        return "movie-form";
    }

    @PostMapping("/save-movie")
    public String saveMovie(@ModelAttribute("movie") Movie theMovie) {

        // save the employee
        movieService.save(theMovie);


        // use a redirect to prevent duplicate submissions
        return REDIRECT_ADDRESS;
    }

    @GetMapping("/delete-movie")
    public String showFormForDelete(@RequestParam("movieId") int id)
    {

        movieService.deleteById(id);

        return REDIRECT_ADDRESS;
    }

    //method for pagination.

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value="pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize=5;
        Page<Movie> page = movieService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Movie> movies = page.getContent();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserConverter userConverter = new UserConverter();
        UserDTO userDTO = userConverter.entityToDto(userService.findByUsername((name)));
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("movies",movies);
        model.addAttribute("user",userDTO);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc":"asc");


        return "list-movies";
    }


    @GetMapping("/add-review")
    public String showFormForAddReview(@RequestParam("movieId") int id,Model theModel)
    {
        MovieConverter movieConverter = new MovieConverter();
        MovieDTO movieDTO = movieConverter.entityToDto(movieService.findById(id));
        theModel.addAttribute(MOVIE_MODEL_NAME,movieDTO);
        ReviewDTO reviewDTO = new ReviewDTO();
        theModel.addAttribute("review", reviewDTO);

        return "review-form";

    }

    @PostMapping("/save-review")
    public String saveMovie(@RequestParam("movieId") int id,@ModelAttribute("review") Review theReview){
        Movie movie = movieService.findById(id);
        String name = SecurityContextHolder.getContext().getAuthentication().getName(); //**
        User user = userService.findByUsername(name);

        movie.add(theReview);
        theReview.setMovie(movie);
        theReview.setUser(user);
        reviewService.save(theReview);
        return REDIRECT_ADDRESS;
    }

    @GetMapping("/delete-review")
    public String showFormForDeleteReview(@RequestParam("reviewId") int id)
    {
        reviewService.deleteById(id);

        return REDIRECT_ADDRESS;
    }

    @GetMapping("/reviews")
    public String listReviews(Model model)
    {
        List<Review> reviews = reviewService.findAll();
        model.addAttribute(REVIEW_MODEL_NAME,reviews);

        return "list-reviews";
    }

    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);

        return "sign-up";
    }


    @PostMapping("/register")
    public String processRegister(@Valid User user, BindingResult bindingResult, Model model) {

        user.setTheAuthority(new Authority(2));
        List<Review> reviews = new ArrayList<>();
        user.setReviews(reviews);
        user.setEnabled(1);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if (userService.usernameAlreadyExists(user.getUsername())){
            bindingResult.addError(new FieldError("user","username","username already exists"));
        }
        if (bindingResult.hasErrors()){
            return "sign-up";
        }
        userService.save(user);

        return "register-success";
    }

}