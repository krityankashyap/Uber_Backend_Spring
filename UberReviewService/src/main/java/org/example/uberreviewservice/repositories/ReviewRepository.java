package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Long> {

    Integer countAllByRatingsLessThanEqual(Double givenRating);

    List<Review> findAllByRatingsLessThanEqual(Integer givenRating);


}

