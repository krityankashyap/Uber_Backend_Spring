package org.example.uberreviewservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

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
    @Transactional
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

    @Override
    public Review updateReview(Long id, Review newReviewData){
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(newReviewData.getRatings() != null){
            review.setRatings(newReviewData.getRatings());
        }

        if(newReviewData.getContent() != null){
            review.setContent(newReviewData.getContent());
        }

        return this.reviewRepository.save(review);
    }
}
