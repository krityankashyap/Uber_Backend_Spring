package org.example.uberprojectauthservice.controllers;

import org.example.uberprojectauthservice.dtos.AuthRequestDto;
import org.example.uberprojectauthservice.dtos.PassengerDto;
import org.example.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import org.example.uberprojectauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService , AuthenticationManager authenticationManager){
        this.authService= authService;
        this.authenticationManager= authenticationManager;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){

        PassengerDto response= authService.signupPassenger(passengerSignupRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto){
        System.out.println("Incoming req " + authRequestDto.getPassword() + " " + authRequestDto.getEmail());
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequestDto.getEmail(),   // username
                                authRequestDto.getPassword() // password
                        )
                );
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>("Auth successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Auth unsuccessful", HttpStatus.OK);
    }


}
