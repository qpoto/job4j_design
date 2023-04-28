package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter  {
    public static List<String> filter(String file) {
        List<String> log404 = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            log404 = in.lines().filter(n -> n.contains(" 404 ")).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log404;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.println(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("data/log.txt");
        for (String logString : log) {
            System.out.println(logString);
        }
        List<String> log1 = filter("data/log.txt");
        save(log1, "data/404.txt");
    }
}