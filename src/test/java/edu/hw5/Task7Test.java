package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("test 1.1")
    void test11() {

        var s = "00";

        assertThat(Task7.threeOrMoreThirdIsZero(s)).isFalse();

    }

    @Test
    @DisplayName("test 1.2")
    void test12() {

        var s = "0010000";

        assertThat(Task7.threeOrMoreThirdIsZero(s)).isFalse();

    }

    @Test
    @DisplayName("test 1.3")
    void test13() {

        var s = "11011";

        assertThat(Task7.threeOrMoreThirdIsZero(s)).isTrue();

    }

    @Test
    @DisplayName("test 1.4")
    void test14() {

        var s = "110";

        assertThat(Task7.threeOrMoreThirdIsZero(s)).isTrue();

    }

    @Test
    @DisplayName("test 1.5")
    void test15() {

        var s = "1102";

        assertThat(Task7.threeOrMoreThirdIsZero(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.1")
    void test21() {

        var s = "0";

        assertThat(Task7.startEqualsEnd(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.2")
    void test22() {

        var s = "1";

        assertThat(Task7.startEqualsEnd(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.3")
    void test23() {

        var s = "01";

        assertThat(Task7.startEqualsEnd(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.4")
    void test24() {

        var s = "1101011010";

        assertThat(Task7.startEqualsEnd(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.5")
    void test25() {

        var s = "11010120101";

        assertThat(Task7.startEqualsEnd(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.6")
    void test26() {

        var s = "1101";

        assertThat(Task7.startEqualsEnd(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.7")
    void test27() {

        var s = "0010";

        assertThat(Task7.startEqualsEnd(s)).isTrue();

    }

    @Test
    @DisplayName("test 3.1")
    void test31() {

        var s = "";

        assertThat(Task7.oneToThree(s)).isFalse();

    }

    @Test
    @DisplayName("test 3.2")
    void test32() {

        var s = "2";

        assertThat(Task7.oneToThree(s)).isFalse();

    }

    @Test
    @DisplayName("test 3.3")
    void test33() {

        var s = "110";

        assertThat(Task7.oneToThree(s)).isTrue();

    }

    @Test
    @DisplayName("test 3.4")
    void test34() {

        var s = "1101";

        assertThat(Task7.oneToThree(s)).isFalse();

    }

}

