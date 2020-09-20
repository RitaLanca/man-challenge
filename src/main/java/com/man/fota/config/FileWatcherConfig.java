package com.man.fota.config;

import com.man.fota.ManFotaChallengeProperties;
import com.man.fota.service.FileService;
import com.man.fota.utils.CustomFileChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.Duration;
import java.util.logging.Logger;

@Configuration
public class FileWatcherConfig {

    private static Logger log = Logger.getLogger(FileWatcherConfig.class.getName());

    @Autowired
    private ManFotaChallengeProperties properties;

    @Autowired
    private ApplicationContext context;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(5000L), Duration.ofMillis(3000L));
        fileSystemWatcher.addSourceDirectory(new File(properties.getFolderDir()));
        fileSystemWatcher.addListener(new CustomFileChangeListener(context.getBean(FileService.class)));
        fileSystemWatcher.start();
        log.info("Waiting for files in " + properties.getFolderDir() +" ....");
        return fileSystemWatcher;
    }

}
