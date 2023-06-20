package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"));
        String[] filters = argsName.get("filter").split(",");
        while (scanner.hasNext()) {
            String[] clmn = scanner.nextLine().split(argsName.get("delimiter"));
            System.out.println(Arrays.toString(clmn));
        }
    }


    private static void validateProgArgs(String[] myArgs) {
        if (myArgs.length != 4) {
            throw new IllegalArgumentException("В программу должно быть передано 4 аргумента");
        }
        if (!(myArgs[1].endsWith(",") || myArgs[1].endsWith(";"))) {
            throw new IllegalArgumentException("Разделителем может быть запятая или точка с запятой");
        }
    }
    public static void main(String[] args) throws Exception {
        validateProgArgs(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}