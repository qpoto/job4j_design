package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validate(ArgsName args) {
        if (!Files.isDirectory(Path.of(args.get("d")))) {
            throw new IllegalArgumentException("The specified argument is not a directory");
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Excluded extensions must start with \".\"");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The archive must have the \".zip\" extension");
        }
        System.out.println("The input arguments are valid.");
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
        if (args.length != 3) {
            throw new IllegalArgumentException("There should be 3 arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> pathList = Search.search(Paths.get(argsName.get("d")), p -> !p.toString().endsWith(argsName.get("e")));
        System.out.println("The list of files has been received");
        Zip zip = new Zip();
        zip.packFiles(pathList, new File(argsName.get("o")));
        System.out.println("Archiving was carried out successfully");
    }
}