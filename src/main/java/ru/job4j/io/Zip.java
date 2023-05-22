package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validate(List<String> args) {
        if (args.size() != 3) {
            throw new IllegalArgumentException("Set all arguments");
        }
        if (!Files.exists(Paths.get(args.get(0)))) {
            throw new IllegalArgumentException(String.format("This folder '%s' does not exist", args.get(0)));
        }
        if (!args.get(1).startsWith(".")) {
            throw new IllegalArgumentException(String.format("This extension '%s' uncorrected", args.get(1)));
        }
    }

    public void packFiles(List<Path> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path p : source) {
                zip.putNextEntry(new ZipEntry(p.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(p.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        Map<String, String> arguments = ArgsName.of(args).values;
        List<String> val = new ArrayList<>();
        for (Map.Entry<String, String> e : arguments.entrySet()) {
            val.add(e.getValue());
        }
        validate(val);
        List<Path> files = Search.search(Paths.get(val.get(0)),
                (e -> !e.toFile().getName().endsWith(val.get(1))));
        zip.packFiles(files, Paths.get(val.get(2)).toFile());
    }
}