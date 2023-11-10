package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    @DisplayName("test 1")
    void test1() {

        var pass1 = "hi world 123";

        assertThat(Task4.validatePass(pass1)).isFalse();

    }

    @Test
    @DisplayName("test 2")
    void test2() {

        var pass2 = "a";

        assertThat(Task4.validatePass(pass2)).isFalse();

    }

    @Test
    @DisplayName("test 3")
    void test3() {

        var pass3 = "*";

        assertThat(Task4.validatePass(pass3)).isTrue();

    }

    @Test
    @DisplayName("test 4")
    void test4() {

        var pass4 = "sd vsv$frse gf||||";

        assertThat(Task4.validatePass(pass4)).isTrue();

    }

    @Test
    @DisplayName("test 5")
    void test5() {

        var pass5 = "";

        assertThat(Task4.validatePass(pass5)).isFalse();
    }
}
