package ru.job4j.io;

import java.io.*;
import java.io.PrintWriter;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new PrintWriter(target))) {
            read.lines()
                    .forEach(line -> {
                        String[] codeTime = line.split(" ", 2);
                        Map<String, String> date = new HashMap<>();
                        date.put(codeTime[0], codeTime[1]);
                        System.out.println(date);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}