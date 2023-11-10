package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    @Test
    @DisplayName("test 1.1")
    void test11() {

        var s = "01";

        assertThat(Task8.unevenLength(s)).isFalse();

    }

    @Test
    @DisplayName("test 1.2")
    void test12() {

        var s = "11";

        assertThat(Task8.unevenLength(s)).isFalse();

    }

    @Test
    @DisplayName("test 1.3")
    void test13() {

        var s = "010";

        assertThat(Task8.unevenLength(s)).isTrue();

    }

    @Test
    @DisplayName("test 1.4")
    void test14() {

        var s = "01000";

        assertThat(Task8.unevenLength(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.1")
    void test21() {

        var s = "1";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.2")
    void test22() {

        var s = "100";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isFalse();

    }

    @Test
    @DisplayName("test 2.3")
    void test23() {

        var s = "1111";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.4")
    void test24() {

        var s = "0";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.5")
    void test25() {

        var s = "0111010";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isTrue();

    }

    @Test
    @DisplayName("test 2.6")
    void test26() {

        var s = "01";

        assertThat(Task8.unevenFromZeroOrEvenFromOne(s)).isFalse();

    }

    @Test
    @DisplayName("test 3.1")
    void test31() {

        var s = "00";

        assertThat(Task8.zerosDivisibleByThree(s)).isFalse();

    }

    @Test
    @DisplayName("test 3.2")
    void test32() {

        var s = "0001010";

        assertThat(Task8.zerosDivisibleByThree(s)).isFalse();

    }

    @Test
    @DisplayName("test 3.3")
    void test33() {

        var s = "000";

        assertThat(Task8.zerosDivisibleByThree(s)).isTrue();

    }

    @Test
    @DisplayName("test 3.4")
    void test34() {

        var s = "01001000111";

        assertThat(Task8.zerosDivisibleByThree(s)).isTrue();

    }

    @Test
    @DisplayName("test 4.1")
    void test41() {

        var s = "11";

        assertThat(Task8.exceptTwoOrThreeOnes(s)).isFalse();

    }

    @Test
    @DisplayName("test 4.2")
    void test42() {

        var s = "111";

        assertThat(Task8.exceptTwoOrThreeOnes(s)).isFalse();

    }

    @Test
    @DisplayName("test 4.3")
    void test43() {

        var s = "1111";

        assertThat(Task8.exceptTwoOrThreeOnes(s)).isTrue();

    }

    @Test
    @DisplayName("test 5.1")
    void test51() {

        var s = "1";

        assertThat(Task8.noConseqOnes(s)).isTrue();

    }

    @Test
    @DisplayName("test 5.2")
    void test52() {

        var s = "101010010100010010";

        assertThat(Task8.noConseqOnes(s)).isTrue();

    }

    @Test
    @DisplayName("test 5.3")
    void test53() {

        var s = "1001001100100010";

        assertThat(Task8.noConseqOnes(s)).isFalse();

    }

}
