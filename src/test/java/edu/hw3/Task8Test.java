package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка для ArrayList<Integer>")
    void listIntTest() {
        // given
        var list = new ArrayList<Integer>();
        list.add(3);
        list.add(-1);
        list.add(0);
        var reversedList = new ArrayList<Integer>();
        var iter = new BackwardIterator<>(list);

        // when
        while (iter.hasNext()) {
            reversedList.add(iter.next());
        }

        // then
        assertThat(reversedList).isEqualTo(list.reversed());
    }

    @Test
    @DisplayName("Проверка для LinkedList<String>")
    void lListStrTest() {
        // given
        var list = new LinkedList<String>();
        list.add("c");
        list.add("mid");
        list.add("seems like a");
        var reversedList = new LinkedList<String>();
        var iter = new BackwardIterator<>(list);

        // when
        while (iter.hasNext()) {
            reversedList.add(iter.next());
        }

        // then
        assertThat(reversedList).isEqualTo(list.reversed());
    }
}
