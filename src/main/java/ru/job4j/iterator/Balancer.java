package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int start = 0;
        while (source.hasNext()) {
            nodes.get(start).add(source.next());
            start++;
            if (start >= nodes.size()) {
                start = 0;
            }
        }
    }
}