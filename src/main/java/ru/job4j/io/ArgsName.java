package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName  {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '%s' is missing".formatted(key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            chekArgs(arg);
            String[] keyValue = arg.split("=", 2);
            String key = keyValue[0].substring(1);
            String value = keyValue[1];
            values.put(key, value);
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

    private static void chekArgs(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '%s' does not start with a '-' character".formatted(arg));
        }
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException("Error: This argument '%s' does not contain a key".formatted(arg));
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '%s' does not contain an equal sign".formatted(arg));
        }
        if (arg.indexOf("=") == arg.length() - 1) {
            throw new IllegalArgumentException("Error: This argument '%s' does not contain a value".formatted(arg));
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}