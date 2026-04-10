package org.example.uberreviewservice.dtos;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private Double ratings;
    private Long booking;

    private Date createdAt;
    private Date updatedAt;


}
