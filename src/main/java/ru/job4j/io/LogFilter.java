package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> log404 = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            log404 = in.lines().filter(n -> n.contains(" 404 ")).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log404;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String logString : log) {
            System.out.println(logString);
        }
    }
}