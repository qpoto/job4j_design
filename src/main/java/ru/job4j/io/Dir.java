package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        System.out.printf("size: %s%n", file.getTotalSpace());
        for (File subfile : file.listFiles()) {
            if (validateProgArgs(args)) {
                System.out.println(subfile.getName());
            }
        }
    }

    public static boolean validateProgArgs(String[] myArgs) {
        if (myArgs[0] == null) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (!myArgs[1].contains(".txt")) {
            throw new IllegalArgumentException("File extension not specified");
        }
        return true;
    }
}