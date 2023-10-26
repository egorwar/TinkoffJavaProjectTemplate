package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T extends List<E>, E> implements Iterator<E> {

    private final List<E> list;
    private int currentPointer;

    public BackwardIterator(List<E> list) {
        this.list = list;
        currentPointer = list.size() - 1;
    }

    // returns false if next element does not exist
    public boolean hasNext() {
        return currentPointer >= 0;
    }

    // return current data and update pointer
    public E next() {
        return list.get(currentPointer--);
    }
}
