package ru.job4j.io;

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
        while (run) {
            Scanner cc = new Scanner(System.in);
            String userSay = cc.nextLine();
            if (userSay.equals(CONTINUE)) {
                System.out.println("БОТ: Отлично! Продолжаем диалог! Какой вопрос на этот раз? :-)");
                notSilent = true;
            }
            if (userSay.equals(STOP)) {
                System.out.println("БОТ: Молчу - молчу...");
                notSilent = false;
            }
            if (userSay.equals(OUT)) {
                System.out.println("БОТ: До новых встреч!");
                run = false;
                notSilent = false;
            }
            if (notSilent) {
                String answer = "ОТВЕТ";
                System.out.println("БОТ: " + answer);
            }
        }
    }

    private List<String> readPhrases() {
        return null;
    }

    private void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        System.out.println("Добрый день! Давайте поговорим!");
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}