package org.example.uberprojectauthservice.dtos;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;

    private String name;

    private String email;

    private String password; // hashed password

    private String phoneNumber;

    private Date createdAt;
}
