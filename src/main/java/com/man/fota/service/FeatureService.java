package com.man.fota.service;

import com.man.fota.ManFotaChallengeProperties;
import com.man.fota.domain.entity.Configuration;
import com.man.fota.domain.entity.Feature;
import com.man.fota.domain.entity.Vehicle;
import com.man.fota.domain.repository.FeatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FeatureService {

    private static Logger log =  Logger.getLogger(FeatureService.class.getName());

    private ManFotaChallengeProperties properties;

    private FeatureRepository featureRepository;

    public FeatureService(ManFotaChallengeProperties properties, FeatureRepository featureRepository) {
        this.properties = properties;
        this.featureRepository = featureRepository;
    }

    public void loadFeatures() {
        log.info("Loading features....");
        featureRepository.saveAll(properties.getFeatures());
        log.info("All " + properties.getFeatures().size() + " features were loaded.");
    }

    public List<Feature> getAllCompatibleFeatures(Vehicle vehicle) {
        List<Feature> features = featureRepository.findAll();

        List<Feature> allowedFeature = new ArrayList<>();

        features.stream().forEach(feature -> {
            boolean validMandatorySoftwareCode = validateMandatoryConfigurationCode(vehicle.getSoftwareCodes(),feature.getSoftwareCodesMandatory());
            boolean validMandatoryHardwareCode = validateMandatoryConfigurationCode(vehicle.getHardwareCodes(),feature.getHardwareCodesMandatory());
            boolean hasInvalidSoftwareCode = hasInvalidConfigurationCode(vehicle.getSoftwareCodes(),feature.getSoftwareCodesInvalid());
            boolean hasInvalidHardwareCode = hasInvalidConfigurationCode(vehicle.getHardwareCodes(),feature.getHardwareCodesInvalid());

            if(validMandatorySoftwareCode && validMandatoryHardwareCode && !hasInvalidSoftwareCode && !hasInvalidHardwareCode) {
                allowedFeature.add(feature);
            }
        });

        return allowedFeature;
    }

    public List<Feature> getAllIncompatibleFeatures(Vehicle vehicle) {
        List<Feature> features = featureRepository.findAll();

        List<Feature> incompatibleFeatures = new ArrayList<>();

        features.stream().forEach(feature -> {
            boolean validMandatorySoftwareCode = validateMandatoryConfigurationCode(vehicle.getSoftwareCodes(),feature.getSoftwareCodesMandatory());
            boolean validMandatoryHardwareCode = validateMandatoryConfigurationCode(vehicle.getHardwareCodes(),feature.getHardwareCodesMandatory());
            boolean hasInvalidSoftwareCode = hasInvalidConfigurationCode(vehicle.getSoftwareCodes(),feature.getSoftwareCodesInvalid());
            boolean hasInvalidHardwareCode = hasInvalidConfigurationCode(vehicle.getHardwareCodes(),feature.getHardwareCodesInvalid());

            if(!validMandatorySoftwareCode || !validMandatoryHardwareCode || hasInvalidSoftwareCode || hasInvalidHardwareCode) {
                incompatibleFeatures.add(feature);
            }
        });

        return incompatibleFeatures;
    }


    private boolean validateMandatoryConfigurationCode(List<Configuration> vehicleCodes, List<Configuration> featureMandatoryConfigurationCodes) {
        return featureMandatoryConfigurationCodes.stream().allMatch(softwareCode -> vehicleCodes.contains(softwareCode));
    }

    private boolean hasInvalidConfigurationCode(List<Configuration> vehicleConfigurationCodes, List<Configuration> featureInvalidConfigurationCodes) {
        return featureInvalidConfigurationCodes.stream().anyMatch(softwareCode -> vehicleConfigurationCodes.contains(softwareCode));
    }

}
