package ru.job4j.io;

import java.io.*;
import java.io.PrintWriter;

public class Analysis {
    private boolean check = true;
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            read.lines()
                    .forEach(line -> {
                        String[] codeTime = line.split(" ");
                        if (check == (line.contains("400") || line.contains("500"))) {
                            out.append(codeTime[1]).append(";").append(check ? "" : System.lineSeparator());
                            check = !check;
                        }
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