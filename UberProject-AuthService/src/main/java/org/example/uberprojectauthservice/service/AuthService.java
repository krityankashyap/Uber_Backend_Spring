package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.dtos.PassengerDto;
import org.example.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import org.example.uberprojectauthservice.models.Passenger;
import org.example.uberprojectauthservice.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private PassengerRepository passengerRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository , BCryptPasswordEncoder bCryptPasswordEncoder){
        this.passengerRepository= passengerRepository;
        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger= Passenger.builder()
                        .name(passengerSignupRequestDto.getName())
                                .email(passengerSignupRequestDto.getEmail())
                                        .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword()))
                                                .phone_number(passengerSignupRequestDto.getPhone_number())
                                                     .build();

        Passenger newPassenger= passengerRepository.save(passenger);

        return PassengerDto.from(newPassenger);
    }
}
