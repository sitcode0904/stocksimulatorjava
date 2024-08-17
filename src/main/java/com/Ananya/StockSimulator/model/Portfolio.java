package com.Ananya.StockSimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    private List<Stock> stocks;

    public Portfolio() {
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public double getTotalPortfolioValue() {
        return stocks.stream().mapToDouble(Stock::getTotalValue).sum();
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
