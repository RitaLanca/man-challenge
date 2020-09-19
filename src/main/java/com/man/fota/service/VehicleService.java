package com.man.fota.service;

import com.man.fota.domain.entity.Vehicle;
import com.man.fota.domain.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService( VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Vehicle> findVehicle(String vin) {
        return vehicleRepository.findById(vin);
    }

    public List<Vehicle> importVehicles(List<Vehicle> vehicles) {
        return vehicleRepository.saveAll(vehicles);
    }

    public List<Vehicle> findAllVehicle() {
        return vehicleRepository.findAll();
    }
}
