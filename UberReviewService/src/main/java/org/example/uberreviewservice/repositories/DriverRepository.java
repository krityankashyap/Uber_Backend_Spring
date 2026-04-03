package org.example.uberreviewservice.repositories;

import org.example.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true , value = "SELECT * FROM Driver WHERE id = :id AND license_number = :license") // raw sql query
    Optional<Driver> rawfindByIdAndlicenseNumber(Long id, String license);

    @Query("SELECT d FROM Driver AS d WHERE d.Id= :id AND d.licenseNumber= :license") // hibernate query
    Optional<Driver> hibernatefindByIdAndlicenseNumber(@Param("id") Long id, @Param("license") String license);

    List<Driver> findAllByIdIn(List<Long> driverId);
}
