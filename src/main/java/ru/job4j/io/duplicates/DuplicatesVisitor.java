package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> fPs = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toAbsolutePath().toString());
        if (!fPs.containsKey(fileProperty)) {
            fPs.put(fileProperty, new ArrayList<>());
        }
        fPs.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void printPath() {
        for (List<Path> path : fPs.values()) {
            if (path.size() >= 2) {
                System.out.println(fPs.values());
            }
        }
    }
}