package org.example.uberreviewservice.controllers;

import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
