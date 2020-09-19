package com.man.fota;

import com.man.fota.domain.Resource;
import com.man.fota.domain.response.FeaturesResource;
import com.man.fota.service.FotaService;
import org.awaitility.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 Integrated Test that test end to end application
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class ITManFotaChallengeApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FotaService fotaService;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        fotaService.loadFeatures();

        int countFeatures = jdbcTemplate.queryForObject("select count(*) from feature", Integer.class);
        assertEquals(countFeatures, 3);

        File hardwareFileSource = new File("src/test/resources/files/hard_001.csv");
        File softwareFileSource = new File("src/test/resources/files/soft_001.csv");
        File hardwareFileDest = new File("src/test/resources/man-challenge/hard_001.csv");
        File softwareFileDest = new File("src/test/resources/man-challenge/soft_001.csv");

        try {
            Files.copy(hardwareFileSource.toPath(), hardwareFileDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(softwareFileSource.toPath(), softwareFileDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        await()
                .pollInterval(Duration.ONE_SECOND)
                .await().atMost(new Duration(20, TimeUnit.SECONDS))
                .until(() -> ((jdbcTemplate.queryForObject("select count(*) from vehicle", Integer.class)) > 0));

        int countVehicle = jdbcTemplate.queryForObject("select count(*) from vehicle", Integer.class);
        assertEquals(countVehicle, 138);

        ResponseEntity<Resource> response = restTemplate.exchange("http://localhost:" + port + "/fota/vehicles/WAUHFBFL1DN549274/installable", HttpMethod.GET, HttpEntity.EMPTY, Resource.class);

        assertNotNull(response.getBody());
        List<String> features = (List<String>) ((LinkedHashMap<String, FeaturesResource>) response.getBody().getBody()).get("features");
        assertEquals(features.size(), 1);
        assertEquals(features.get(0), "B");
    }

    @AfterEach
    public void revertData () {
        File hardwareFileDest = new File("src/test/resources/man-challenge/");
        for(File file : hardwareFileDest.listFiles()){
            file.delete();
        }
    }
}
