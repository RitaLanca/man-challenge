package com.man.fota.service;

import com.man.fota.domain.entity.Feature;
import com.man.fota.domain.entity.Vehicle;
import com.man.fota.exception.VinNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FotaService {

    private static Logger log = Logger.getLogger(FotaService.class.getName());

    private VehicleService vehicleService;
    private FeatureService featureService;

    public FotaService(VehicleService vehicleService, FeatureService featureService) {
        this.vehicleService = vehicleService;
        this.featureService = featureService;
    }

    public List<String> getAllInstallableFeaturesForVin(String vin) {

        Optional<Vehicle> vehicle = vehicleService.findVehicle(vin);

        return featureService.getAllCompatibleFeatures(vehicle.orElseThrow(() -> new VinNotFoundException(vin))).stream()
                .map(Feature::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllIncompatibleFeaturesForVin(String vin) {

        Optional<Vehicle> vehicle = vehicleService.findVehicle(vin);

        return featureService.getAllIncompatibleFeatures(vehicle.orElseThrow(() -> new VinNotFoundException(vin))).stream()
                .map(Feature::getName)
                .collect(Collectors.toList());

    }

    public void loadFeatures() {
        log.info("Loading features....");
        featureService.loadFeatures();
        log.info("Features loaded");
    }

}
