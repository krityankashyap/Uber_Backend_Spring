package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true , value = "SELECT * FROM Driver WHERE id = :id AND license_number = :license") // raw sql query
    Optional<Driver> rawfindByIdAndlicenseNumber(Long id, String license);

    @Query("SELECT Driver FROM Driver WHERE id= :id AND licenseNumber= :license") // hibernate query
    Optional<Driver> hibernatefindByIdAndlicenseNumber(Long id, String license);
}
