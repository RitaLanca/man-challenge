package com.man.fota.utils;

import com.man.fota.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;

@Component
public class CustomFileChangeListener implements FileChangeListener {

    @Autowired
    private FileService fileService;

    public CustomFileChangeListener(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles : changeSet) {
            for(ChangedFile changedFile: cfiles.getFiles()) {
                if( !isLocked(changedFile.getFile().toPath())) {
                    fileService.processFileInformation(changedFile.getFile());
                    fileService.deleteFile(changedFile.getFile());
                }
            }
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }

}