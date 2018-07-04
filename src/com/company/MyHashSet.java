package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyHashSet<E> implements MySet<E> {

    private final static int DEFAULT_CAPACITY = 16;
    private final static int MAXIMUM_CAPACITY = 1 << 30;
    private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    private int capacity;
    private float loadFactor;
    private int size;
    private LinkedList<E>[] table;

    public MyHashSet() {
        this(DEFAULT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity, float loadFactor) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOF2(initialCapacity);
        this.loadFactor = loadFactor;
        table = new LinkedList[capacity];
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                table[i].clear();
        }
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        if (size + 1 > capacity * loadFactor) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
        }
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<E>();
        }
        table[bucketIndex].add(e);
        size++;
        return true;
    }

    @Override
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            for (E element : bucket)
                if (element.equals(e))
                    return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        if (!contains(e)) {
            return false;
        }
        int bucketIndex = hash(e.hashCode());

        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            for (E element : bucket)
                if (e.equals(element)) {
                    bucket.remove(element);
                    break;
                }
        }
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        List<E> list = setToList();

        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int trimToPowerOF2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        return capacity;
    }

    @Override
    public String toString() {
        List<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < list.size() - 1; i++)
            builder.append(list.get(i)).append(", ");
        if (list.size() == 0)
            builder.append("]");
        else
            builder.append(list.get(list.size() - 1)).append("]");
        return builder.toString();
    }

    private ArrayList<E> setToList() {
        ArrayList<E> list = new ArrayList<E>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                list.addAll(table[i]);
        }
        return list;
    }

}