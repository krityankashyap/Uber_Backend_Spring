package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Driver;
import org.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Long> {

//    List<Booking> findAllByDriverId(Long driverId);
//
//    List<Booking> findAllByDriverIn(List<Driver> drivers);

//    @Query("select r from Booking b inner join Review r on b.review = r where b.Id = :bookingId")
//    Review findReviewByBookingId(Long bookingId);
}
