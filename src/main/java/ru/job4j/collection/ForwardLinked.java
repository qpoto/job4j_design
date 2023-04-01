package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        ForwardLinked.Node<T> serv = head;
        if (size == 0) {
            head = new ForwardLinked.Node<>(value, null);
        } else {
            for (int i = 1; i < size; i++) {
                serv = serv.next;
            }
            serv.next = new ForwardLinked.Node<>(value, null);
        }
        size++;
        modCount++;    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> service = head;
        for (int i = 0; i < index; i++) {
            service = service.next;
        }
        modCount++;
        return service.item;    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        head = head.next;
        return head.item;
    }

    public void addFirst(T value) {
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ForwardLinked.Node<T> node = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T res = node.item;
                node = node.next;
                return res;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private ForwardLinked.Node<E> next;

        Node(E element, ForwardLinked.Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}