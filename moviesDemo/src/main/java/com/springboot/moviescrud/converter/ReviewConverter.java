package com.springboot.moviescrud.converter;

import com.springboot.moviescrud.dto.ReviewDTO;
import com.springboot.moviescrud.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {
    public ReviewDTO entityToDto(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewContent(review.getReviewContent());
        reviewDTO.setMovie(review.getMovie());
        reviewDTO.setUser(review.getUser());
        return reviewDTO;

    }

    public Review dtoToEntity(ReviewDTO reviewDTO)
    {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setReviewContent(reviewDTO.getReviewContent());
        review.setMovie(reviewDTO.getMovie());
        review.setUser(reviewDTO.getUser());
        return review;
    }

    public List<ReviewDTO> entityToDto(List<Review> reviews)
    {
        return  reviews.stream().map(this::entityToDto).collect(Collectors.toList());

    }
    public List<Review> dtoToEntity(List<ReviewDTO> reviewDTOList){

        return reviewDTOList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
