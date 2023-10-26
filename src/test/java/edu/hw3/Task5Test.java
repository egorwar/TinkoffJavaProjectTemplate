package edu.hw3;

import edu.hw3.task5.Order;
import edu.hw3.task5.Person;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("Проверка на восходящую сортировку по фамилиям")
    void lastAsc() {
        // given
        String[] contacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        List<Person> trueSorted = Arrays.asList(
            new Person("Thomas", "Aquinas"),
            new Person("Rene", "Descartes"),
            new Person("David", "Hume"),
            new Person("John", "Locke")
        );

        // when
        List<Person> gotSorted = Task5.parseContacts(contacts, Order.ASC);

        // then
        assertThat(gotSorted).isEqualTo(trueSorted);
    }

    @Test
    @DisplayName("Проверка на низходящую сортировку по фамилиям и именам")
    void bothDesc() {
        // given
        String[] contacts = new String[] {"John Locke", "Abraham", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        List<Person> trueSorted = Arrays.asList(

            new Person("John", "Locke"),
            new Person("David", "Hume"),
            new Person("Rene", "Descartes"),
            new Person("Thomas", "Aquinas"),
            new Person("Abraham", null)
        );

        // when
        List<Person> gotSorted = Task5.parseContacts(contacts, Order.DESC);

        // then
        assertThat(gotSorted).isEqualTo(trueSorted);
    }

    @Test
    @DisplayName("Проверка на сортировку пустого массива")
    void emptySort() {
        // given
        String[] contacts = new String[0];
        List<Person> trueSorted = new ArrayList<>();

        // when
        List<Person> gotSorted = Task5.parseContacts(contacts, Order.DESC);

        // then
        assertThat(gotSorted).isEqualTo(trueSorted);
    }

    @Test
    @DisplayName("Проверка на сортировку null")
    void nullSort() {
        // given
        String[] contacts = null;
        List<Person> trueSorted = new ArrayList<>();

        // when
        List<Person> gotSorted = Task5.parseContacts(contacts, Order.DESC);

        // then
        assertThat(gotSorted).isEqualTo(trueSorted);
    }

    @Test
    @DisplayName("слишком много или мало слов в контакте")
    void upperBoundTest() {
        // given
        String[] contacts = new String[] {"Augustin Louis Cauchy"};

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Task5.parseContacts(contacts, Order.ASC),
            "Expected Task5.parseContacts to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Each contact should be a [<name> <lastName>] or a [<name>] string");
    }
}
