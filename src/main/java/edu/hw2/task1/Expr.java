package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr value, double power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), power);
        }
    }

    public record Addition(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() + value2.evaluate();
        }
    }

    public record Multiplication(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() * value2.evaluate();
        }
    }
}
