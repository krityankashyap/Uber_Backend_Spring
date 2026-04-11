package org.example.uberprojectauthservice.dtos;

import lombok.*;
import org.example.uberprojectauthservice.models.Passenger;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private Long id;

    private String name;

    private String email;

    private String password; // hashed password

    private String phone_number;

    private Date createdAt;

    public static PassengerDto from (Passenger p){
        PassengerDto result= PassengerDto.builder()
                .id(p.getId())
                .name(p.getName())
                .email(p.getEmail())
                .createdAt(p.getCreatedAt())
                .password(p.getPassword())
                .phone_number(p.getPhone_number())
                .build();

        return result;
    }
}
