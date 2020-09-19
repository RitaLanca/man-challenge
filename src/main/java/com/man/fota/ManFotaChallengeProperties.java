package com.man.fota;

import com.man.fota.domain.entity.Feature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ManFotaChallengeProperties {

    private String folderDir;

    private List<Feature> features;

    public String getFolderDir() {
        return folderDir;
    }

    public void setFolderDir(String folderDir) {
        this.folderDir = folderDir;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}

