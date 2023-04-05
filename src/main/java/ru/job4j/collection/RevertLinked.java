package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RevertLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        boolean reverted = false;
        if (head != null) {
            Node<T> current = head.next; // явный промежуточный указатель на 2ой элемент коллекции
            head.next = null; // разрыв связи между головой и 2ым элементом коллекции
            while (current != null) { // до того момента, пока 2ой элемент коллекции будет существовать
                Node<T> next = current.next; // 3ий элемент коллекцииЮ на который явно указывает 2ой
                current.next = head; // разворачиваем ссылку от 2го элемента на 1ый (голову)
                head = current; // голова перемещается на следующий элемент коллекции
                current = next;// тут next получается "стирается", и при следующем проходе создается снова
            }
            reverted = current == null;
        }
        return reverted;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}