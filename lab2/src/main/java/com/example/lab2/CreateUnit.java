package com.example.lab2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateUnit {
    public static void CreateNewUnit(String sName, String sHealth, String sX, String sY){

        if (sName.equals("")) sName="Unit"+HelloApplication.counter;
        double h;
        try {
            h = Double.parseDouble(sHealth);
        }
        catch (Exception e){
            h = 0.0;
        }

        double x;
        try {
            x = Double.parseDouble(sX);
        }
        catch (Exception e){
            x = 0.0;
        }

        double y;
        try {
            y = Double.parseDouble(sY);
        }
        catch (Exception e){
            y = 0.0;
        }
        System.out.printf("Створено новий мікрооб'єкт ID=%s Name=%s Health=%s X=%s Y=%s\n", HelloApplication.counter, sName, Double.toString(h), sX, sY);
        HelloApplication.herd.add(new Soldier(sName, h, x, y));

    }
    public static void display(double x, double y){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Уведіть параметри нового солдату!");
        window.setMinWidth(250);
        VBox layout = new VBox(11);
        //layout.getChildren().addAll();
        layout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label();
        nameLabel.setText("NAME: ");
        TextField nameText = new TextField();


        Label hpLabel = new Label();
        hpLabel.setText("HP: ");
        TextField healthText = new TextField();

        Label xLabel = new Label();
        xLabel.setText("X: ");
        TextField xText = new TextField();
        xText.setText(Double.toString(x));

        Label yLabel = new Label();
        yLabel.setText("Y: ");
        TextField yText = new TextField();
        yText.setText(Double.toString(y));



        Button okButton = new Button("OK");
        okButton.setOnAction(e->{


            String sName = nameText.getText();
            String sHealth = healthText.getText();
            String sX = xText.getText();
            String sY = yText.getText();
            CreateNewUnit(sName, sHealth, sX, sY);
            HelloApplication.counter++;
            window.close();
        });

        layout.getChildren().addAll(nameLabel, nameText, hpLabel, healthText, xLabel, xText, yLabel, yText, okButton);

        Scene scene = new Scene(layout, 303, 303);
        window.setScene(scene);
        window.showAndWait();
    }
}
