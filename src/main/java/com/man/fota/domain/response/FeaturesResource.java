package com.man.fota.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FeaturesResource {

    private List<String> features;

    public FeaturesResource(List<String> features) {
        this.features = features;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
