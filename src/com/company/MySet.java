package com.company;

public interface MySet<E> {

    boolean add(E e);

    void clear();

    boolean contains(Object o);

    boolean isEmpty();

    boolean remove(Object o);

    int size();

    Object[] toArray();

}