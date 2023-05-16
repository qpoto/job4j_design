package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Map<FileProperty, List<String>> fPs = new HashMap<>();
        fPs.put(new FileProperty(attrs.size(), file.toString()), new ArrayList<>());
        System.out.println(fPs);
        return super.visitFile(file, attrs);
    }
}