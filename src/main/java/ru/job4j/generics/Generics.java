package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> animals = new ArrayList<>();
        List<Predator> predators = new ArrayList<>();
        List<Tiger> tigers = new ArrayList<>();
        animals.add(new Animal());
        predators.add(new Predator());
        tigers.add(new Tiger());

        gen.printObject(animals);
        gen.printObject(predators);
        gen.printObject(tigers);
        System.out.println();

/*        gen.printBoundedWildCard(animals); - нельзя запихнуть Animal, т.к. сюда можно запихнуть только тех кто,
                                               наследует Predator, т.е. Tiger, включая обе границы. Если бы у Tiger был
                                               наследник, его сюда тоже нельзя запихнуть.
                                               */
        gen.printBoundedWildCard(predators);
        gen.printBoundedWildCard(tigers);
        System.out.println();

        gen.printLowerBoundedWildCard(animals);
        gen.printLowerBoundedWildCard(predators);
/*        gen.printLowerBoundedWildCard(tigers); - нельзя запихнуть Tiger, т.к. сюда можно запихнуть только тех кто,
                                                   входит в диапазон от Predator до Animal, включая обе границы.
                                                   */
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}