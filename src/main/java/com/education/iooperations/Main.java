package com.education.iooperations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Turn package >> ");
        String pathName = scanner.nextLine();
        System.out.print("Turn word >> ");
        String word = scanner.nextLine();
        Path path = Paths.get(pathName);
        if (Files.isDirectory(path)) {
            new Thread(new TxtSearchImpl(path, word)).start();
        } else {
            if (path.getFileName().endsWith(".txt"))
                new Thread(new WordSearchImpl(path, word)).start();
        }
    }
}
