package org.example.uberreviewservice.Adapters;

import org.example.uberreviewservice.dtos.CreateReviewDto;
import org.example.uberreviewservice.models.Booking;
import org.example.uberreviewservice.models.Review;
import org.example.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter{

    private BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository){
        this.bookingRepository= bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking= bookingRepository.findById(createReviewDto.getBookingId());
        if(booking.isEmpty()){
            return null;
        }
        Review review= Review.builder()
                .content(createReviewDto.getContent())
                .ratings(createReviewDto.getRatings())
                .booking(booking.get())
                .build();

        return review;
    }
}
