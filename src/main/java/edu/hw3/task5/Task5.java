package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task5 {

    private Task5() {
    }

    public static List<Person> parseContacts(String[] strContacts, Order order) {

        List<Person> contacts = new ArrayList<>();

        if (strContacts == null || strContacts.length == 0) {
            return contacts;
        }

        for (String strCredentials : strContacts) {

            String[] credentials = strCredentials.split(" ");

            switch (credentials.length) {
                case 1 -> contacts.add(new Person(credentials[0], null));
                case 2 -> contacts.add(new Person(credentials[0], credentials[1]));
                default -> throw new IllegalArgumentException(
                    "Each contact should be a [<name> <lastName>] or a [<name>] string");
            }
        }

        Collections.sort(contacts);

        return switch (order) {
            case ASC -> contacts;
            case DESC -> {
                Collections.reverse(contacts);
                yield contacts;
            }
        };

    }

}
