package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(line -> line.contains("=") && !line.startsWith("#")).filter(Config::lineFilter).forEach(line -> {
                    String[] keyValue = line.split("=", 2);
                    String key = keyValue[0];
                    String value = keyValue[1];
                    values.put(key, value);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean lineFilter(String line) {
        if (line.startsWith("=")) {
            throw new IllegalArgumentException("Line must have key");
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException("Line must have value");
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String path = "./data/error3.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(config.values);
    }
}