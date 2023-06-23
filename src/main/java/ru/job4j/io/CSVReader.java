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
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"))) {
            String[] filters = argsName.get("filter").split(argsName.get("delimiter"));
            String[] splitFilters = scanner.nextLine().split(argsName.get("delimiter"));
            String[] finishFilter = new String[splitFilters.length];
            for (int i = 0; i < filters.length; i++) {
                for (int j = 0; j < splitFilters.length; j++) {
                    if (filters[i].equals(splitFilters[j])) {
                        finishFilter[i] = splitFilters[j];
                        break;
                    }
                }
            }
            for (String filter : filters) {
                System.out.print(filter + argsName.get("delimiter"));
            }
            System.out.println();
            while (scanner.hasNextLine()) {
                String[] column = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < column.length; i++) {
                    if (finishFilter[i] != null) {
                        int index = Arrays.asList(splitFilters).indexOf(finishFilter[i]);
                        String print = column[index];
                        System.out.print(print + argsName.get("delimiter"));
                    }
                }
                System.out.println();
            }
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