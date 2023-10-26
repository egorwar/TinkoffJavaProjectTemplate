package edu.hw3;

import edu.hw3.task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Проверка на добавление ключа null")
    void nullTest() {
        // given
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());

        // when
        tree.put(null, "test");

        // then
        assertThat(tree.containsKey(null)).isTrue();
    }
}
