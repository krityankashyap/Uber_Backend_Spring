package org.example.uberprojectauthservice.service;

import org.example.uberprojectauthservice.Helpers.AuthPassengerDetails;
import org.example.uberprojectauthservice.models.Passenger;
import org.example.uberprojectauthservice.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is responsible for loading the user in the form of UserDetails for the auth
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private  PassengerRepository passengerRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger= passengerRepository.findPassengerByEmail(email); // email is the unique identifier

        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        } else {
            throw new UsernameNotFoundException("No passenger with corresponding email");
        }
    }
}
