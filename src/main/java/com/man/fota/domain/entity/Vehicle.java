package com.man.fota.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Vehicle {

    @Id
    @Column
    private String vin;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> softwareConfigurations = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> hardwareConfigurations = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<Configuration> getSoftwareCodes() {
        return softwareConfigurations;
    }

    public void setSoftwareCodes(List<Configuration> softwareConfigurations) {
        this.softwareConfigurations = softwareConfigurations;
    }

    public List<Configuration> getHardwareCodes() {
        return hardwareConfigurations;
    }

    public void setHardwareCodes(List<Configuration> hardwareConfigurations) {
        this.hardwareConfigurations = hardwareConfigurations;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", softwareConfigurations=" + softwareConfigurations +
                ", hardwareConfigurations=" + hardwareConfigurations +
                '}';
    }
}
