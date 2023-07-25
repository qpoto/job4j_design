package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Milk {
    private boolean pasteurized;
    private int volume;
    private String name;
    private Farm farm;
    private int[] pfc = new int[2];

    public Milk(boolean pasteurized, int volume, String name, Farm farm, int[] pfc) {
        this.pasteurized = pasteurized;
        this.volume = volume;
        this.name = name;
        this.farm = farm;
        this.pfc = pfc;
    }

    @Override
    public String toString() {
        return "Milk{"
                + "pasteurized=" + pasteurized
                + ", volume=" + volume
                + ", name='" + name
                + '\''
                + ", farm=" + farm
                + ", pfc=" + Arrays.toString(pfc)
                + '}';
    }

    public static void main(String[] args) {
        Farm kozino = new Farm("Kozino", 100);
        int[] pfc = {3, 3, 5};
        Milk parmalat = new Milk(true, 1, "Parmalat", kozino, pfc);

        Gson gson = new GsonBuilder().create();
        String milk = gson.toJson(parmalat);
        System.out.println(milk);

        Milk parmalatJson = gson.fromJson(milk, Milk.class);
        System.out.println(parmalatJson);


    }
}
