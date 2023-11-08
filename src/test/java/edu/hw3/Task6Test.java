package edu.hw3;

import edu.hw3.task6.PriorityStockMarket;
import edu.hw3.task6.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    @DisplayName("Проверка на обычном примере")
    void addExample() {
        // given
        PriorityStockMarket market = new PriorityStockMarket();
        market.add(new Stock(123));
        market.add(new Stock(1.999));
        market.add(new Stock(300.24));
        market.add(new Stock(300.1));
        market.add(new Stock(20.5));

        // when
        double maxValue = 300.24;

        // then
        assertThat(market.mostValuableStock().getValue()).isEqualTo(maxValue);
    }

    @Test
    @DisplayName("Проверка на изменение при удалении максимума")
    void removeExample() {
        // given
        PriorityStockMarket market = new PriorityStockMarket();

        Stock topStock = new Stock(300.24);

        market.add(new Stock(123));
        market.add(new Stock(1.999));
        market.add(topStock);
        market.add(new Stock(300.1));
        market.add(new Stock(20.5));

        market.remove(topStock);
        // when
        double maxValue = 300.1;

        // then
        assertThat(market.mostValuableStock().getValue()).isEqualTo(maxValue);
    }

    @Test
    @DisplayName("Проверка на некорректное объявление акции")
    void incorrectInitTest() {
        // given
        double negValue = -12.5;
        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new Stock(negValue),
            "Expected new Stock(negValue) to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Stock value cannot be negative");
    }

    @Test
    @DisplayName("Проверка на некорректное изменение акции")
    void incorrectSetTest() {
        // given
        Stock correctStock = new Stock(100);
        double negValue = -12.5;
        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> correctStock.setValue(negValue),
            "Expected correctStock.setValue(negValue) to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo(
            "Stock value cannot be negative");
    }
}
