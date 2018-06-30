package com.company;

import java.util.HashMap;

public class MyHashSet<E> implements MySet<E> {

    private HashMap<E, Object> map;
    private final Object DEFAULT_OBJECT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, DEFAULT_OBJECT) == null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == DEFAULT_OBJECT;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[map.size()];
        int i = 0;
        for (E e: map.keySet()) {
            array[i] = e;
            i++;
        }
        return array;
    }
}