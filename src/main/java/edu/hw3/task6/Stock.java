package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {

    private double value;

    public Stock(double value) {
        setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {

        if (value < 0) {
            throw new IllegalArgumentException("Stock value cannot be negative");
        }

        this.value = value;
    }

    /** Некорректное "инвертирующее" сравнение, чтобы PriorityQueue всегда хранила самую ценную акцию спереди */
    @Override
    public int compareTo(@NotNull Stock s) {
        return Double.compare(s.getValue(), this.value);
    }
}
