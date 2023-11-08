package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Person(String name, String lastName) implements Comparable<Person> {

    @Override
    public int compareTo(@NotNull Person p) {
        String firstValue = (this.lastName == null) ? this.name : this.lastName;
        String secondValue = (p.lastName == null) ? p.name : p.lastName;

        return firstValue.compareTo(secondValue);
    }
}
