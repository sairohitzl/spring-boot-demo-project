package com.springboot.moviescrud.service;

import com.springboot.moviescrud.dao.ReviewRepository;
import com.springboot.moviescrud.entity.Review;
import com.springboot.moviescrud.exceptions.MyRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }
    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int theId) {
        Optional<Review> result = reviewRepository.findById(theId);
        Review theReview=null;
        if(result.isPresent()){
            theReview = result.get();
        }
        else
        {
            throw new MyRunTimeException("Did not  find movie of id: "+theId);
        }
        return theReview;
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(int theId) {
        reviewRepository.deleteById(theId);
    }
}