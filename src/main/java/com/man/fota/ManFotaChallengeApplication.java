package com.man.fota;

import com.man.fota.service.FotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackageClasses = ManFotaChallengeProperties.class)
@EnableConfigurationProperties
public class ManFotaChallengeApplication implements CommandLineRunner {

    @Autowired
    private FotaService service;

    public static void main(String[] args) {
        SpringApplication.run(ManFotaChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        service.loadFeatures();
    }
}
