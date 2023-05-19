package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static void validateProgArgs(String[] myArgs) {
        File dir = new File(myArgs[0]);
        if (myArgs.length != 2) {
            throw new IllegalArgumentException("2 arguments must be passed to the program");
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Is not a directory");
        }

        if (!myArgs[1].startsWith(".") && myArgs[1].length() <= 1) {
            throw new IllegalArgumentException("File extension not specified");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        validateProgArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile()
                .getName()
                .endsWith(args[1]))
                .forEach(System.out::println);
    }
}