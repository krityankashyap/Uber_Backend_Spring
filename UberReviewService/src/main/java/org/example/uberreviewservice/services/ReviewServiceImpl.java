package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public Review publishReview(Review review){
        return this.reviewRepository.save(review);
    }


    @Override
    public boolean DeleteReviewById(Long id) {
         try{
             reviewRepository.deleteById(id);
             return true;
         } catch (Exception e) {
             return false;
         }

    }
}
