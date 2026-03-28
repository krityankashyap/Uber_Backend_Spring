package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("************");

        Review r= Review.
                   builder().
                   content("Best quality ride").
                   ratings(5.0).
                   build();

        reviewRepository.save(r); // this code executes sql query

        List<Review> reviews = reviewRepository.findAll();

        for(Review review : reviews){
            System.out.println(review.getContent());
        }

        reviewRepository.deleteAllById(Collections.singleton(2L));
    }


}
