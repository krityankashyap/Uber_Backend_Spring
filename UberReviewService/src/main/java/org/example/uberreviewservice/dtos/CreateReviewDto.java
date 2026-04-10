package org.example.uberreviewservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewDto {

    private String content;
    private Double ratings;
    private long bookingId;
}
