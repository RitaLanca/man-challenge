package com.man.fota.domain.repository;

import  com.man.fota.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
