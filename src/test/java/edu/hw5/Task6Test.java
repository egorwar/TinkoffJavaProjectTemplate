package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    @DisplayName("test 1")
    void test1() {

        var src = "abc";
        var sub = "abc";

        assertThat(Task6.isSubstring(sub, src)).isTrue();

    }

    @Test
    @DisplayName("test 2")
    void test2() {

        var src = "ab";
        var sub = "abc";

        assertThat(Task6.isSubstring(sub, src)).isFalse();

    }

    @Test
    @DisplayName("test 3")
    void test3() {

        var src = "abac";
        var sub = "abc";

        assertThat(Task6.isSubstring(sub, src)).isFalse();

    }

    @Test
    @DisplayName("test 4")
    void test4() {

        var src = "abacababcaaaa";
        var sub = "abc";

        assertThat(Task6.isSubstring(sub, src)).isTrue();

    }
}

