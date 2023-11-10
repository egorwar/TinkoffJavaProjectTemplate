package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Проверка на обычную дату")
    void commonDateTest() {
        // given
        var date = "2020-10-9";
        var expectedDate = Optional.of(LocalDate.of(2020, 10, 9));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на дату через /")
    void slashDateTest() {
        // given
        var date = "1/3/2020";
        var expectedDate = Optional.of(LocalDate.of(2020, 3, 1));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на tomorrow")
    void tomorrowTest() {
        // given
        var date = "tomorrow";
        var expectedDate = Optional.of(LocalDate.of(2023, 11, 11));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на today")
    void todayTest() {
        // given
        var date = "today";
        var expectedDate = Optional.of(LocalDate.of(2023, 11, 10));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на yesterday")
    void yesterdayTest() {
        // given
        var date = "yesterday";
        var expectedDate = Optional.of(LocalDate.of(2023, 11, 9));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на day(s) ago")
    void agoTest() {
        // given
        var date = "11 days ago";
        var expectedDate = Optional.of(LocalDate.of(2023, 10, 30));

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

    @Test
    @DisplayName("Проверка на нераспознанный стиль")
    void falseTest() {
        // given
        var date = "11 long days ago";
        var expectedDate = Optional.empty();

        // when
        var trueDate = Task3.parseDate(date);

        // then
        assertThat(trueDate).isEqualTo(expectedDate);
    }

}
