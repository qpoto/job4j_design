package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int i;
        if (capacity * LOAD_FACTOR >= count) {
            expand();
        }
        if (key == null) {
            i = 0;
        } else {
            i = indexFor(hash(key.hashCode()));
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] doubleTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].key == null) {
                    doubleTable[0] = table[i];
                } else {
                    int j = indexFor(hash(table[i].key.hashCode()));
                    doubleTable[j] = table[i];
                }
            }
        }
        table = doubleTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        if (key == null) {
            if (table[0] != null && table[0].key == null) {
                result = table[0].value;
            }
        } else {
            int i = indexFor(hash(key.hashCode()));
            if (table[i] != null && key.equals(table[i].key)) {
                result = table[i].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (key == null) {
            if (table[0] != null && table[0].key == null) {
                table[0] = null;
                result = true;
                count--;
                modCount++;
            }
        } else {
            int i = indexFor(hash(key.hashCode()));
            if (table[i] != null && table[i].key.equals(key)) {
                table[i] = null;
                result = true;
                count--;
                modCount++;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity - 1 && table[index] == null) {
                    index++;
                }
                return table[index] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}