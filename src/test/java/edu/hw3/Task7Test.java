package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    @DisplayName("Проверка на добавление ключа null")
    void nullTest() {
        // given
        TreeMap<String, String> tree = new TreeMap<>(Comparator.nullsFirst(
            Comparator.comparingInt(String::length)
        ));

        // when
        tree.put(null, "test");

        // then
        assertThat(tree.containsKey(null)).isTrue();
    }
}
