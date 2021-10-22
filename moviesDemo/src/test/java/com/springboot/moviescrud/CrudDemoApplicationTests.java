package com.springboot.moviescrud;

import com.springboot.moviescrud.controller.DemoController;
import com.springboot.moviescrud.controller.LoginController;
import com.springboot.moviescrud.controller.MovieRestController;
import com.springboot.moviescrud.dao.MovieRepository;
import com.springboot.moviescrud.dao.ReviewRepository;
import com.springboot.moviescrud.dao.UserRepository;
import com.springboot.moviescrud.entity.Authority;
import com.springboot.moviescrud.entity.Movie;
import com.springboot.moviescrud.entity.Review;
import com.springboot.moviescrud.entity.User;
import com.springboot.moviescrud.service.MovieService;
import com.springboot.moviescrud.service.ReviewService;
import com.springboot.moviescrud.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CrudDemoApplicationTests {


	@Autowired
	private MovieService movieService;

	@MockBean
	private MovieRepository movieRepository;

	@Autowired
	private ReviewService reviewService;

	@MockBean
	private ReviewRepository reviewRepository;

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	int id=0;
	static List<Review> reviews = CrudDemoApplicationTests.getReviews();
	static Authority authority = new Authority(1);

	static private List<Review> getReviews()
	{
		Review review1 = new Review("amazing movie");
		Review review2 = new Review("wonderful movie");
		List<Review> reviews = new ArrayList<>();
		reviews.add(review1);
		reviews.add(review2);
		return reviews;
	}

	@Test
	 void findAllTestMovie() {
		when(movieRepository.findAll()).thenReturn(Stream.of(new Movie("movie1", 50, "genre1"),
				new Movie("movie1", 50, "genre1")).collect(Collectors.toList()));

		assertEquals(2, movieService.findAll().size());
	}

	@Test
	 void findByIdTestMovie() {
		int id = 0;
		Movie movie = new Movie("movie1", 50, "genre1");
		when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
		assertEquals(movie, movieService.findById(0));

	}

	@Test
	 void saveMovie()
	{
		Movie movie = new Movie("movie2", 50, "genre2");
		movieService.save(movie);
		verify(movieRepository,times(1)).save(movie);
	}

	@Test
	 void deleteByIdTestMovie(){
		Movie movie = new Movie("movie2", 50, "genre2");
		movieService.deleteById(0);
		verify(movieRepository,times(1)).deleteById(0);
	}



	@Test
	 void findAllTestReview() {
		when(reviewRepository.findAll()).thenReturn(Stream.of(new Review("greatest movie"),
				new Review("one of the best movie")).collect(Collectors.toList()));

		assertEquals(2, reviewService.findAll().size());
	}

	@Test
	 void findByIdTestReview() {
		int id = 0;
		Review review = new Review("crazyy movie");
		when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
		assertEquals(review, reviewService.findById(0));

	}

	@Test
	 void saveReview()
	{
		Review review = new Review("crazyy movie");
		reviewService.save(review);
		verify(reviewRepository,times(1)).save(review);
	}

	@Test
	 void deleteByIdTestReview(){
		Review review = new Review("crazyy movie");
		reviewService.deleteById(0);
		verify(reviewRepository,times(1)).deleteById(0);
	}

	@Test
	 void findAllTestUser() {
		when(userRepository.findAll())
				.thenReturn(Stream.of(
								new User(1,"karthik",
										"password123",1,reviews,authority),
								new User(2,"sujay",
										"password123",1,reviews,authority)).
						collect(Collectors.toList()));

		assertEquals(2, userService.findAll().size());
	}

	@Test
	 void findByIdTestUser() {
		int id = 1;
		User user =  new User(1,"karthik",
				"password123",1,reviews,authority);
		when(userRepository.findById(id)).thenReturn(Optional.of(user));
		assertEquals(user, userService.findById(1));

	}

	@Test
	 void saveUser()
	{
		User user =  new User(1,"karthik",
				"password123",1,reviews,authority);
		userService.save(user);
		verify(userRepository,times(1)).save(user);
	}

	@Test
	 void deleteByIdTestUser(){
		User user =  new User(1,"karthik",
				"password123",1,reviews,authority);
		userService.deleteById(1);
		verify(userRepository,times(1)).deleteById(1);
	}

	@Test
	void showHome() {
		DemoController demoController = new DemoController(); //Arrange
		String response = demoController.showHome();          //Act
		Assertions.assertEquals("redirect:/login-page",response); //Assert
	}

	@Test
	void loginPage()
	{
		LoginController loginController = new LoginController();
		String response = loginController.showMyLoginPage();
		Assertions.assertEquals("new-login",response);
	}


}
