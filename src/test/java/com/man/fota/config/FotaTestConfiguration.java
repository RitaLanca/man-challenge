package com.man.fota.config;

import com.man.fota.ManFotaChallengeProperties;
import com.man.fota.domain.repository.FeatureRepository;
import com.man.fota.service.FeatureService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
public class FotaTestConfiguration {

    @Bean
    @Profile("!integration")
    public ManFotaChallengeProperties properties () {
        return new ManFotaChallengeProperties();
    }

    @Bean
    @Profile("!integration")
    public FeatureRepository featureRepository(){
        return Mockito.mock(FeatureRepository.class);
    }

    @Bean
    @Profile("!integration")
    public FeatureService featureService(){
        return new FeatureService(properties(),featureRepository());
    }

}
