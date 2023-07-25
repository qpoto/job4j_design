package ru.job4j.serialization.java;

public class Farm {
    String name;
    int cowsQuantity;

    public Farm(String name, int cowsQuantity) {
        this.name = name;
        this.cowsQuantity = cowsQuantity;
    }

    @Override
    public String toString() {
        return "Farm{"
                + "name='" + name + '\''
                + ", cowsQuantity=" + cowsQuantity
                + '}';
    }
}
