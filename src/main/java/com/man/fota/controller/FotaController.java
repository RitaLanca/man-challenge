package com.man.fota.controller;

import com.man.fota.domain.Resource;
import com.man.fota.domain.response.FeaturesResource;
import com.man.fota.service.FotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fota" )
public class FotaController {

    private FotaService fotaService;

    public FotaController(FotaService fotaService) {
        this.fotaService = fotaService;
    }

    @GetMapping(value = "/vehicles/{vin}/installable")
    public ResponseEntity<Resource<FeaturesResource>> getAllInstallableFeaturesForVin(@PathVariable String vin){
        return ResponseEntity.ok(new Resource<>("successful operation",new FeaturesResource(fotaService.getAllInstallableFeaturesForVin(vin))));
    }

    @GetMapping(value = "/vehicles/{vin}/incompatible")
    public ResponseEntity<Resource<FeaturesResource>> getAllIncompatibleFeaturesForVin(@PathVariable String vin){
        return ResponseEntity.ok(new Resource<>("successful operation",new FeaturesResource(fotaService.getAllIncompatibleFeaturesForVin(vin))));

    }
}
