package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    @DisplayName("test 1")
    void test1() {

        var plate1 = "А123ВЕ777";

        assertThat(Task5.validateRusRegPlate(plate1)).isTrue();

    }

    @Test
    @DisplayName("test 2")
    void test2() {

        var plate2 = "О777ОО177";

        assertThat(Task5.validateRusRegPlate(plate2)).isTrue();

    }

    @Test
    @DisplayName("test 3")
    void test3() {

        var plate3 = "123АВ777";

        assertThat(Task5.validateRusRegPlate(plate3)).isFalse();

    }

    @Test
    @DisplayName("test 4")
    void test4() {

        var plate4 = "А123ВГ77";

        assertThat(Task5.validateRusRegPlate(plate4)).isFalse();

    }

    @Test
    @DisplayName("test 5")
    void test5() {

        var plate5 = "А123ВЕ7777";

        assertThat(Task4.validatePass(plate5)).isFalse();
    }
}

