package com.Ananya.StockSimulator.view;

import com.Ananya.StockSimulator.controller.MarketController;
import com.Ananya.StockSimulator.model.Portfolio;
import com.Ananya.StockSimulator.model.Stock;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DashboardView {

    private BorderPane view;
    private Portfolio portfolio;
    private MarketController marketController;
    private ListView<String> stockListView;

    public DashboardView() {
        portfolio = new Portfolio();
        marketController = new MarketController();
        view = new BorderPane();

        stockListView = new ListView<>();
        view.setCenter(stockListView);

        createUI();
    }

    private void createUI() {
        HBox inputBox = new HBox(10);
        inputBox.setPadding(new Insets(10));
        
        TextField tickerField = new TextField();
        tickerField.setPromptText("Enter Stock Ticker");
        
        TextField quantityField = new TextField();
        quantityField.setPromptText("Enter Quantity");
        
        Button addButton = new Button("Add Stock");
        addButton.setOnAction(e -> {
            String ticker = tickerField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = marketController.fetchStockPrice(ticker);
            Stock stock = new Stock(ticker, price, quantity);
            portfolio.addStock(stock);
            updateStockList();
        });
        
        inputBox.getChildren().addAll(new Label("Ticker:"), tickerField, new Label("Quantity:"), quantityField, addButton);
        
        view.setTop(inputBox);
    }

    private void updateStockList() {
        Platform.runLater(() -> {
            stockListView.getItems().clear();
            for (Stock stock : portfolio.getStocks()) {
                stockListView.getItems().add(stock.getTicker() + ": " + stock.getQuantity() + " @ " + stock.getPrice());
            }
        });
    }

    public BorderPane getView() {
        return view;
    }
}
