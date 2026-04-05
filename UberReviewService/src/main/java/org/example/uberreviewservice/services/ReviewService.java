package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public Optional<Review> findReviewById(Long id);

    public List<Review> findAllReview();

    public boolean DeleteReviewById(Long id);
}
