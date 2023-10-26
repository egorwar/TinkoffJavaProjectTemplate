package edu.hw3;

import edu.hw3.task4.Task4;
import edu.hw3.task5.Task5;
import edu.hw3.task6.PriorityStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task8.BackwardIterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw3.task5.Order.DESC;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void main(String[] args) {
        // Task 1
        LOGGER.info(Task1.atbash("Hello world!"));
        // Task 2
        LOGGER.info(Task2.clusterize("(sg(sag(a)gfg)g(hdf)g)(j(fghdg)j(sgs(fh)sfh()dhfsd)dsfh)dhh"));
        // Task 3
        LOGGER.info(Task3.freqDict(new String[] {"код", "код", "код", "bug"}));
        LOGGER.info(Task3.freqDict(new Integer[] {1, 2, 1, 2}));
        // Task 4
        LOGGER.info(Task4.arabicToRoman(19));
        // Task 5
        LOGGER.info(Task5.parseContacts(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes",
            "Fedya"}, DESC));
        // Task 6
        PriorityStockMarket market = new PriorityStockMarket();
        market.add(new Stock(123));
        market.add(new Stock(1.999));
        market.add(new Stock(300.24));
        market.add(new Stock(300.1));
        market.add(new Stock(20.5));

        LOGGER.info(market.mostValuableStock().getValue());

        // Task 7
        // goto Task7Test

        // Task 8
        var it = new BackwardIterator<>(List.of(1, 2, 3));
        while (it.hasNext()) {
            LOGGER.info(it.next());
        }

    }
}
