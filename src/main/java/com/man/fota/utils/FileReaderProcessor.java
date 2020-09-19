package com.man.fota.utils;

import com.man.fota.domain.entity.Configuration;
import com.man.fota.domain.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class FileReaderProcessor {

    private final static Logger log = Logger.getLogger(FileReaderProcessor.class.getName());


    private static String COMMA_DELIMITER = ",";


    public static List<Vehicle> readFile(File file, List<Vehicle> vehicleList) {
        log.info("Reading file from directory: " + file.getAbsolutePath());
        boolean isHardware = file.getName().startsWith("hard");
        List<Vehicle> vehicles = vehicleList;
        try {
            Files.lines(file.toPath()).forEach(line -> {
                String[] values = line.split(COMMA_DELIMITER);
                Vehicle vehicle = vehicles.stream().filter(v -> v.getVin().equals(values[0])).findFirst().orElse(new Vehicle(values[0]));
                if(isHardware){
                    log.info("HARDWARE CODE: Add code "+ values[1] + " in vin " + vehicle.getVin());
                    vehicle.getHardwareCodes().add(Configuration.ConfigurationBuilder.builder()
                            .code(values[1])
                            .build());
                }else {
                    log.info("SOFTWARE CODE: Add code "+ values[1] + " in vin " + vehicle.getVin());
                    vehicle.getSoftwareCodes().add(Configuration.ConfigurationBuilder.builder()
                            .code(values[1])
                            .build());
                }

                vehicles.add(vehicle);
            });
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicles;
    }


}
