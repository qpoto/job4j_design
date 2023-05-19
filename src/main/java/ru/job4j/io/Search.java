package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static boolean validateProgArgs(String[] myArgs) {
        if (myArgs[0] == null) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (!myArgs[1].equals("123")) {
            throw new IllegalArgumentException("File extension not specified");
        }

        if (!myArgs[2].contains(".txt")) {
            throw new IllegalArgumentException("File extension not specified");
        }
        return true;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        if (validateProgArgs(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile()
                    .getName()
                    .endsWith(args[1]))
                    .forEach(System.out::println);
        }
    }
}