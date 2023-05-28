package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean run = true;
        boolean notSilent = true;
        List<String> logPath = new ArrayList<>();
        List<String> botAnswers = readPhrases();
        while (run) {
            Scanner cc = new Scanner(System.in);
            String userSay = cc.nextLine().toLowerCase();
            logPath.add("Пользователь: " + userSay);
            if (CONTINUE.equals(userSay)) {
                System.out.println("БОТ: Отлично! Продолжаем диалог! Какой вопрос на этот раз? :-)");
                notSilent = true;
                continue;
            }
            if (STOP.equals(userSay) && notSilent) {
                System.out.println("БОТ: Молчу - молчу...");
                notSilent = false;
            }
            if (OUT.equals(userSay)) {
                String botSayBye = "БОТ: До новых встреч!";
                System.out.println(botSayBye);
                run = false;
                notSilent = false;
                logPath.add(botSayBye);
            }
            if (notSilent) {
                String answer = botAnswers.get((int) (Math.random() * botAnswers.size()));
                System.out.println("БОТ: " + answer);
                logPath.add("БОТ: " + answer);
            }
        }
        saveLog(logPath);
    }

    private List<String> readPhrases() {
        try {
            return Files.readAllLines(new File(botAnswers).toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("Что то не так с файлом ОТВЕТОВ");
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8)))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Добрый день! Давайте поговорим!");
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
        System.out.println("Запись диалога произведена в файл: " + args[0]);
    }
}