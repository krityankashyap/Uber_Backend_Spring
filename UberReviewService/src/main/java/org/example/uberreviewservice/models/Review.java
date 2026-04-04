package org.example.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "booking_review")
public class Review extends BaseClass {


    @Column(nullable = false)
    private String content;

    private Double ratings;



}
