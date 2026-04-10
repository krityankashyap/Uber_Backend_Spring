package org.example.uberreviewservice.controllers;

import org.example.uberreviewservice.Adapters.CreateReviewDtoToReviewAdapter;
import org.example.uberreviewservice.dtos.CreateReviewDto;
import org.example.uberreviewservice.dtos.ReviewDto;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService , CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter){
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter= createReviewDtoToReviewAdapter;
    }


    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request){
        Review incomingRequest= this.createReviewDtoToReviewAdapter.convertDto(request);
        if(incomingRequest == null){
            return new ResponseEntity<>("Invalid argument", HttpStatus.BAD_REQUEST);
        }
        Review review = this.reviewService.publishReview(incomingRequest);
        ReviewDto response= ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .booking(review.getBooking().getId())
                .ratings(review.getRatings())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response , HttpStatus.CREATED);
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



