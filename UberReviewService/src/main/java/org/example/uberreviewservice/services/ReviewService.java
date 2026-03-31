package org.example.uberreviewservice.services;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Driver;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.BookingRepository;
import org.example.uberreviewservice.repositories.DriverRepository;
import org.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {

    private final DriverRepository driverRepository;
    ReviewRepository reviewRepository;

    BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository){
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("************");
//
//        Review r= Review.
//                   builder().
//                   content("Best quality ride").
//                   ratings(5.0).
//                   build();
//
//        Booking b= Booking.builder()
//                        .endTime(new Date())
//                                .review(r)
//                                        .build();
//
//       // reviewRepository.save(r); // this code executes sql query
//
//        bookingRepository.save(b);
//
//        List<Review> reviews = reviewRepository.findAll();
//
//        for(Review review : reviews){
//            System.out.println(review.getContent());
//        }
//
//        reviewRepository.deleteAllById(Collections.singleton(2L));

        Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "DL12121");

        if(driver.isPresent()){
            System.out.println(driver.get().getName());

            List<Booking> b= driver.get().getBookings();

            for(Booking booking : b){
                System.out.println(String.valueOf(booking.getId()));
            }

////            List<Booking> bookings = bookingRepository.findAllByDriverId(1L);
////            for(Booking booking : bookings){
////                System.out.println(booking.getBookingStatus());
////            }
        }
   }


}
