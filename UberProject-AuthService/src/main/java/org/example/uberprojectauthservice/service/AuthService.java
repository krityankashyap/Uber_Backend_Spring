package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.dtos.PassengerDto;
import org.example.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import org.example.uberprojectauthservice.models.Passenger;
import org.example.uberprojectauthservice.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private PassengerRepository passengerRepository;

    public AuthService(PassengerRepository passengerRepository){
        this.passengerRepository= passengerRepository;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger= Passenger.builder()
                        .name(passengerSignupRequestDto.getName())
                                .email(passengerSignupRequestDto.getEmail())
                                        .password(passengerSignupRequestDto.getPassword()) // TODO: Encrypt the password
                                                .phone_number(passengerSignupRequestDto.getPhone_number())
                                                     .build();

        Passenger newPassenger= passengerRepository.save(passenger);

        return PassengerDto.from(newPassenger);
    }
}
