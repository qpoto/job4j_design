package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public Predicate<Path> getCondition() {
        return condition;
    }

    public void setCondition(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String s: lines) {
            if (s.contains("fk")) {
                System.out.println(file.toAbsolutePath());
                break;
            }
        }

        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return new ArrayList<>();
    }
}
