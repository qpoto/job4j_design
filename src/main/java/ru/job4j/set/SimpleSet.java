package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean add = false;
        int ad = 0;
        while (iterator().hasNext()) {
            if (Objects.equals(value, iterator().next())) {
                ad++;
                break;
            }
        }
        if (ad == 0) {
            set.add(value);
            add = true;
        }
        return add;
    }

    @Override
    public boolean contains(T value) {
        boolean cont = false;
        while (iterator().hasNext()) {
            if (Objects.equals(value, iterator().next())) {
                cont = true;
                break;
            }
        }
        return cont;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}