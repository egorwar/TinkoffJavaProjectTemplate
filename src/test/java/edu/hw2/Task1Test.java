package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Проверка константы")
    void constTest() {
        // given
        double val = -2;

        // when
        double same = new Expr.Constant(val).evaluate();

        // then
        assertThat(val).isEqualTo(same);
    }

    @Test
    @DisplayName("Проверка обращения")
    void negateTest() {
        // given
        double val = -2;
        Expr constant = new Expr.Constant(val);

        // when
        double negated = new Expr.Negate(constant).evaluate();

        // then
        assertThat(negated).isEqualTo(-val);
    }

    @Test
    @DisplayName("Проверка обращения нуля")
    void negateZeroTest() {
        // given
        double val = 0;
        Expr constant = new Expr.Constant(val);

        // when
        double negated = new Expr.Negate(constant).evaluate();

        // then
        assertThat(negated).isEqualTo(val);
    }

    @Test
    @DisplayName("Проверка возведения в степень")
    void exponentTest() {
        // given
        double val = 2;
        double power = 3;
        Expr constant = new Expr.Constant(val);

        // when
        double exponentiated = new Expr.Exponent(constant, power).evaluate();

        // then
        assertThat(exponentiated).isEqualTo(Math.pow(val, power));
    }

    @Test
    @DisplayName("Проверка возведения в степень 0")
    void exponentZeroTest() {
        // given
        double minValue = Double.MIN_VALUE;
        double maxValue = Double.MAX_VALUE;
        double power = 0;
        Expr minConstant = new Expr.Constant(minValue);
        Expr maxConstant = new Expr.Constant(maxValue);

        // when
        double minExponentiated = new Expr.Exponent(minConstant, power).evaluate();
        double maxExponentiated = new Expr.Exponent(maxConstant, power).evaluate();

        // then
        assertThat(minExponentiated).isEqualTo(maxExponentiated).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка на сложение")
    void additionTest() {
        // given
        double val1 = -13.5;
        double val2 = 13.5;
        Expr constant1 = new Expr.Constant(val1);
        Expr constant2 = new Expr.Constant(val2);

        // when
        double sum = new Expr.Addition(constant1, constant2).evaluate();

        // then
        assertThat(sum).isEqualTo(val1 + val2).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка на умножение")
    void multiplicationTest() {
        // given
        double val1 = 13.5;
        double val2 = 1 / val1;
        Expr constant1 = new Expr.Constant(val1);
        Expr constant2 = new Expr.Constant(val2);

        // when
        double mult = new Expr.Multiplication(constant1, constant2).evaluate();

        // then
        assertThat(mult).isEqualTo(val1 * val2).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка на умножение на 0")
    void multiplyByZeroTest() {
        // given
        double minValue = Double.MIN_VALUE;
        double maxValue = Double.MAX_VALUE;

        Expr minConstant = new Expr.Constant(minValue);
        Expr maxConstant = new Expr.Constant(maxValue);
        Expr zeroConstant = new Expr.Constant(0);

        // when
        double minExponentiated = new Expr.Multiplication(minConstant, zeroConstant).evaluate();
        double maxExponentiated = new Expr.Multiplication(maxConstant, zeroConstant).evaluate();

        // then
        assertThat(minExponentiated).isEqualTo(maxExponentiated).isEqualTo(0);
    }

}
