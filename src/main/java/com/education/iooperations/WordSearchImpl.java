package com.education.iooperations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WordSearchImpl implements Runnable, WordSearch {
    private final Path file;
    private final String keyWord;

    WordSearchImpl(Path file, String keyWord) {
        this.file = file;
        this.keyWord = keyWord;

    }

    /**
     * Method run Thread
     */
    @Override
    public void run() {
        try {
            BufferedReader utf = Files.newBufferedReader(file);
            final InputStreamReader cp1251 = new InputStreamReader(Files.newInputStream(file), "Cp1251");

            Integer size = readTxtFile(new BufferedReader(utf));
            if (size == 0) readTxtFile(new BufferedReader(cp1251));
        } catch (IOException ignored) {
        }
    }

    public Integer readTxtFile(BufferedReader reader) throws IOException {
        String inputLine;
        int counter = 0;
        final List<Integer> lines = new ArrayList<>();
        while ((inputLine = reader.readLine()) != null) {
            counter++;
            for (String s : inputLine.split(" ")) {
                if (s.equalsIgnoreCase(keyWord)) lines.add(counter);
            }
        }
        if (!lines.isEmpty())
            System.out.printf("File: %s, count: %s, lines: %s\n", file.getFileName(), lines.size(), lines.toString());
        return lines.size();
    }
}
