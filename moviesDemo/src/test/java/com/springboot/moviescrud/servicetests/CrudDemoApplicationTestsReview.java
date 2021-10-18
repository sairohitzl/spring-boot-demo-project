package com.springboot.moviescrud.servicetests;

import com.springboot.moviescrud.dao.ReviewRepository;
import com.springboot.moviescrud.entity.Review;
import com.springboot.moviescrud.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CrudDemoApplicationTestsReview {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    public void findAllTest() {
        when(reviewRepository.findAll()).thenReturn(Stream.of(new Review("greatest movie"),
                new Review("one of the best movie")).collect(Collectors.toList()));

        assertEquals(2, reviewService.findAll().size());
    }

    @Test
    public void findByIdTest() {
        int id = 0;
        Review review = new Review("crazyy movie");
        when(reviewRepository.findById(id)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.findById(0));

    }

    @Test
    public void save()
    {
        Review review = new Review("crazyy movie");
        reviewService.save(review);
        verify(reviewRepository,times(1)).save(review);
    }

    @Test
    public void deleteByIdTest(){
        Review review = new Review("crazyy movie");
        reviewService.deleteById(0);
        verify(reviewRepository,times(1)).deleteById(0);
    }

}
