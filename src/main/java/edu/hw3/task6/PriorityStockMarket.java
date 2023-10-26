package edu.hw3.task6;

import java.util.PriorityQueue;

public class PriorityStockMarket implements StockMarket {

    private final PriorityQueue<Stock> stocks;

    public PriorityStockMarket() {
        this.stocks = new PriorityQueue<>();
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
