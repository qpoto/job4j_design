package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int outSize = 0;

    public T poll() {
        if (outSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        outSize--;
        return in.pop();
    }

    public void push(T value) {
        in.push(value);
        outSize++;
    }
}