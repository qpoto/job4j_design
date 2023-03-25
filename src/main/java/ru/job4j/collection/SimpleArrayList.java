package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;


    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (index < container.length) {
            container[index] = newValue;
            size++;
            modCount++;
        }
        return container[index];
    }


    @Override
    public T remove(int index) {
        if (index > -1) {
            container[index] = null;
            modCount++;
        }
        return container[index];
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[size++];
            }
        };
    }
}