package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"))) {
            String[] filters = argsName.get("filter").split(",");
            String[] splitFilters = scanner.nextLine().split(argsName.get("delimiter"));
            String[] finishFilter = new String[splitFilters.length];
            for (int i = 0; i < filters.length; i++) {
                for (String splitFilter : splitFilters) {
                    if (filters[i].equals(splitFilter)) {
                        finishFilter[i] = splitFilter;
                        break;
                    }
                }
            }
            List<String> filteredCSV = new ArrayList<>();
            filteredCSV.add(argsName.get("filter").replace(",", argsName.get("delimiter")));
            while (scanner.hasNextLine()) {
                String[] column = scanner.nextLine().split(argsName.get("delimiter"));
                StringJoiner everyString = new StringJoiner(argsName.get("delimiter"));
                for (int i = 0; i < column.length; i++) {
                    if (finishFilter[i] != null) {
                        int index = Arrays.asList(splitFilters).indexOf(finishFilter[i]);
                        String print = column[index];
                        everyString.add(print);
                    }
                }
                filteredCSV.add(everyString.toString());
            }
            if ("stdout".equals(argsName.get("out"))) {
                printResult(filteredCSV);
            } else {
                printInFile(filteredCSV, argsName);
            }
        }
    }

    private static void printResult(List<String> forPrint) {
        for (String line : forPrint) {
            System.out.println(line);
        }
    }
    private static void printInFile(List<String> forPrint, ArgsName argsName) throws IOException {
        FileWriter writer = new FileWriter(argsName.get("out"));
        for (String line : forPrint) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }

    private static void validateProgArgs(String[] myArgs, ArgsName argsName) {
        if (myArgs.length != 4) {
            throw new IllegalArgumentException("В программу должно быть передано 4 аргумента");
        }
        if (!(Files.exists(Path.of(argsName.get("path"))) && argsName.get("path").endsWith(".csv"))) {
            throw new IllegalArgumentException("Файл не существует или имеет неверное расширение");
        }
        if (!(myArgs[1].endsWith(",") || myArgs[1].endsWith(";"))) {
            throw new IllegalArgumentException("Разделителем может быть запятая или точка с запятой");
        }
        if (!("stdout".equals(argsName.get("out")) || argsName.get("out").endsWith(".csv"))) {
            throw new IllegalArgumentException("Некорректно введены аргументы вывода результата");
        }
        if (argsName.get("filter").endsWith("=")) {
            throw new IllegalArgumentException("Не указан фильтр");
        }
    }
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateProgArgs(args, argsName);
        handle(argsName);
    }
}