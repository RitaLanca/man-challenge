package com.man.fota.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Feature {

    @Id
    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> softwareCodesMandatory;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> hardwareCodesMandatory;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> softwareCodesInvalid;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Configuration> hardwareCodesInvalid;

    public Feature() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Configuration> getSoftwareCodesMandatory() {
        return softwareCodesMandatory;
    }

    public void setSoftwareCodesMandatory(List<Configuration> softwareCodesMandatory) {
        this.softwareCodesMandatory = softwareCodesMandatory;
    }

    public List<Configuration> getHardwareCodesMandatory() {
        return hardwareCodesMandatory;
    }

    public void setHardwareCodesMandatory(List<Configuration> hardwareCodesMandatory) {
        this.hardwareCodesMandatory = hardwareCodesMandatory;
    }

    public List<Configuration> getSoftwareCodesInvalid() {
        return softwareCodesInvalid;
    }

    public void setSoftwareCodesInvalid(List<Configuration> softwareCodesInvalid) {
        this.softwareCodesInvalid = softwareCodesInvalid;
    }

    public List<Configuration> getHardwareCodesInvalid() {
        return hardwareCodesInvalid;
    }

    public void setHardwareCodesInvalid(List<Configuration> hardwareCodesInvalid) {
        this.hardwareCodesInvalid = hardwareCodesInvalid;
    }


    public static final class FeatureBuilder {
        private Feature feature;

        private FeatureBuilder() {
            feature = new Feature();
        }

        public static FeatureBuilder builder() {
            return new FeatureBuilder();
        }

        public FeatureBuilder name(String name) {
            feature.setName(name);
            return this;
        }

        public FeatureBuilder softwareCodesMandatory(List<Configuration> softwareCodesMandatory) {
            feature.setSoftwareCodesMandatory(softwareCodesMandatory);
            return this;
        }

        public FeatureBuilder hardwareCodesMandatory(List<Configuration> hardwareCodesMandatory) {
            feature.setHardwareCodesMandatory(hardwareCodesMandatory);
            return this;
        }

        public FeatureBuilder softwareCodesInvalid(List<Configuration> softwareCodesInvalid) {
            feature.setSoftwareCodesInvalid(softwareCodesInvalid);
            return this;
        }

        public FeatureBuilder hardwareCodesInvalid(List<Configuration> hardwareCodesInvalid) {
            feature.setHardwareCodesInvalid(hardwareCodesInvalid);
            return this;
        }

        public Feature build() {
            return feature;
        }
    }
}
