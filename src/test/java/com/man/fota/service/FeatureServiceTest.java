package com.man.fota.service;

import com.man.fota.config.FotaTestConfiguration;
import com.man.fota.domain.entity.Configuration;
import com.man.fota.domain.entity.Feature;
import com.man.fota.domain.entity.Vehicle;
import com.man.fota.domain.repository.FeatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FotaTestConfiguration.class)
class FeatureServiceTest {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureService featureService;


    @Test
    void getAllCompatibleFeatures() {
        //Given
        Vehicle vehicle = new Vehicle();
        vehicle.setSoftwareCodes(Arrays.asList(new Configuration("ABCD1"),new Configuration("EFGH1"),new Configuration("ABCD3"),new Configuration("EFGH3"),new Configuration("IJKL2")));
        vehicle.setHardwareCodes(Arrays.asList(new Configuration("QRST1"),new Configuration("QRST3")));

        List<Feature> features =  new ArrayList<>();
        Feature featureA = Feature.FeatureBuilder.builder()
                .name("A")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD1"),new Configuration("EFGH1")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL1"),new Configuration("MNOP1")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST1")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ1")))
                .build();

        Feature featureB = Feature.FeatureBuilder.builder()
                .name("B")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD2"),new Configuration("EFGH2")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL2"),new Configuration("MNOP2")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST2")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ2")))
                .build();

        Feature featureC = Feature.FeatureBuilder.builder()
                .name("C")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD3"),new Configuration("EFGH3")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL3"),new Configuration("MNOP3")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST3")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ3")))
                .build();

        Collections.addAll(features,featureA,featureB,featureC);

        //When
        Mockito.when(featureRepository.findAll()).thenReturn(features);

        //Then
        List<Feature>  availableFeatures = featureService.getAllCompatibleFeatures(vehicle);

        assertEquals(availableFeatures.size(),2);
        assertEquals(availableFeatures.get(0),featureA);
        assertEquals(availableFeatures.get(1),featureC);
    }

    @Test
    void getAllIncompatibleFeatures() {
        //Given
        Vehicle vehicle = new Vehicle();
        vehicle.setSoftwareCodes(Arrays.asList(new Configuration("ABCD1"),new Configuration("EFGH1"),new Configuration("ABCD3"),new Configuration("EFGH3"),new Configuration("IJKL2")));
        vehicle.setHardwareCodes(Arrays.asList(new Configuration("QRST1"),new Configuration("QRST3")));

        List<Feature> features =  new ArrayList<>();
        Feature featureA = Feature.FeatureBuilder.builder()
                .name("A")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD1"),new Configuration("EFGH1")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL1"),new Configuration("MNOP1")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST1")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ1")))
                .build();

        Feature featureB = Feature.FeatureBuilder.builder()
                .name("B")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD2"),new Configuration("EFGH2")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL2"),new Configuration("MNOP2")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST2")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ2")))
                .build();

        Feature featureC = Feature.FeatureBuilder.builder()
                .name("C")
                .softwareCodesMandatory(Arrays.asList(new Configuration("ABCD3"),new Configuration("EFGH3")))
                .softwareCodesInvalid(Arrays.asList(new Configuration("IJKL3"),new Configuration("MNOP3")))
                .hardwareCodesMandatory(Arrays.asList(new Configuration("QRST3")))
                .hardwareCodesInvalid(Arrays.asList(new Configuration("UVXZ3")))
                .build();

        Collections.addAll(features,featureA,featureB,featureC);

        //When
        Mockito.when(featureRepository.findAll()).thenReturn(features);

        //Then
        List<Feature>  availableFeatures = featureService.getAllIncompatibleFeatures(vehicle);

        assertEquals(availableFeatures.size(),1);
        assertEquals(availableFeatures.get(0),featureB);
    }
}