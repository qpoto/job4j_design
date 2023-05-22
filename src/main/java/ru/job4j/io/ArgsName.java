package ru.job4j.io;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArgsName  {

    Map<String, String> values = new LinkedHashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }
    private void validate(String[] args) {
        for (String s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", s));
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", s));
            }
            if (s.startsWith("-=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", s));
            }
            if (s.substring(s.indexOf('=') + 1).isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", s));
            }
        }
    }
    private void parse(String[] args) {
        validate(args);
        String[] temp;
        for (String s : args) {
            temp = s.substring(1).split("=", 2);
            values.put(temp[0], temp[1]);
        }
    }
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}