package com.man.fota.service;

import com.man.fota.domain.entity.Vehicle;
import com.man.fota.utils.FileReaderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class FileService {

    private static Logger log = Logger.getLogger(FileService.class.getName());

    private VehicleService vehicleService;

    public FileService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void processFileInformation(File file) {
        List<Vehicle> vehiclesList = vehicleService.findAllVehicle();
        log.info("Processing file: " + file.getName());
        Long start = System.currentTimeMillis();
        List<Vehicle> vehicles = vehicleService.importVehicles(FileReaderProcessor.readFile(file,vehiclesList));
        log.info("Saved vehicles: " + vehicles.size());
        log.info("Total time:" + (System.currentTimeMillis() - start));
    }

    public void deleteFile(File file) {
        log.info("Delete file: " + file.getName());
        file.delete();
    }

}
