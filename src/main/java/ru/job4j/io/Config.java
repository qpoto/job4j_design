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
            read.lines().filter(line -> line.contains("=")).forEach(line -> {
                String[] keyValue = line.split("=", 2);
                String key = keyValue[0];
                String value = keyValue[1];
                values.put(key, value);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return key != null ? values.get(key) : null;
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
}