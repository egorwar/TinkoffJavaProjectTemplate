package edu.hw2;

import edu.hw2.task1.Expr;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.managers.FaultyConnectionManager;
import edu.hw2.task4.Task4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        // Task 1
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        LOGGER.info(res + " = " + res.evaluate());

        // Task 2
        // goto tests

        // Task 3
        // не могу придумать тесты
        var exec = new PopularCommandExecutor(new FaultyConnectionManager(), 3);
        exec.updatePackages();

        // Task 4
        LOGGER.info(Task4.callingInfo());
    }
}
