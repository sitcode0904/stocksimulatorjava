package com.Ananya.StockSimulator;

import com.Ananya.StockSimulator.view.DashboardView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        DashboardView dashboardView = new DashboardView();
        root.setCenter(dashboardView.getView());

        primaryStage.setTitle("Stock Market Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



