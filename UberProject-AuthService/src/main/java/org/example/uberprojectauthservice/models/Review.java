package org.example.uberprojectauthservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer",  "handler", "booking"})
public class Review extends BaseClass {


    @Column(nullable = false)
    private String content;

    private Double ratings;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;

}
