package com.education.iooperations;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtSearchImpl implements Runnable, TxtSearch {
    private final Path folder;
    private final String word;


    TxtSearchImpl(Path folder, String word) {
        this.folder = folder;
        this.word = word;
    }

    /**
     * Method for File Search in another Thread
     */
    @Override
    public void run() {
        txtSearch();
    }

    @Override
    public void txtSearch() {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(folder)) {
            for (Path path : paths) {
                if (Files.isDirectory(path)) {
                    new Thread(new TxtSearchImpl(path, word)).start();
                } else {
                    if (path.toString().endsWith(".txt")) {
                        new Thread(new WordSearchImpl(path, word)).start();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}