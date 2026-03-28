package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.BookingRepository;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    ReviewRepository reviewRepository;

    BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository){
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("************");

        Review r= Review.
                   builder().
                   content("Best quality ride").
                   ratings(5.0).
                   build();

        Booking b= Booking.builder()
                        .endTime(new Date())
                                .review(r)
                                        .build();

       // reviewRepository.save(r); // this code executes sql query

        bookingRepository.save(b);

        List<Review> reviews = reviewRepository.findAll();

        for(Review review : reviews){
            System.out.println(review.getContent());
        }

        reviewRepository.deleteAllById(Collections.singleton(2L));
    }


}
