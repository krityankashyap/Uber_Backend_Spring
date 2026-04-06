package org.example.uberreviewservice.controllers;

import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @PostMapping
    public ResponseEntity<Review> publishReview(@RequestBody Review request){
        Review review = this.reviewService.publishReview(request);
        return new ResponseEntity<>(review , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews= this.reviewService.findAllReview();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId){
        try{
            Optional<Review> review= this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = this.reviewService.DeleteReviewById(reviewId);
            if (!isDeleted) {
                return new ResponseEntity<>("Unable to delete the review", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

        @PutMapping("/{reviewId}")
        public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request){
            try {
                Review review = this.reviewService.updateReview(reviewId, request);
                return new ResponseEntity<>(review, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }



