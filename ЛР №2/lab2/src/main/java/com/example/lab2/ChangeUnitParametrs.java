package com.example.lab2;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChangeUnitParametrs {
    public static void change_parametrs(ArrayList<String> paramsTochange) {
        Stage window = new Stage();
        Label nameLabel=new Label();
        nameLabel.setText("Name:");
        TextField nameText = new TextField();
        nameText.setText(paramsTochange.get(1));

        Label healthLabel=new Label();
        healthLabel.setText("Health:");
        TextField healthText = new TextField();
        healthText.setText(paramsTochange.get(2));

        Label xLabel=new Label();
        xLabel.setText("X:");
        TextField xText = new TextField();
        xText.setText(paramsTochange.get(3));

        Label yLabel=new Label();
        yLabel.setText("Y:");
        TextField yText = new TextField();
        yText.setText(paramsTochange.get(4));

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);
        Button okButton=new Button("OK");
        okButton.setOnAction(e->{

            String sName= nameText.getText();
            String sHealth= healthText.getText();
            String sX = xText.getText();
            String sY = yText.getText();
            int sID = Integer.parseInt(paramsTochange.get(0));
            HelloApplication.changeSoldier(sID, sName, sHealth, sX, sY);

            window.close(); });


        layout.getChildren().addAll(nameLabel, nameText, healthLabel, healthText, xLabel, xText, yLabel, yText, okButton);

        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void display(double x, double y) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Уведіть параметри нового солдату!");
        window.setMinWidth(250);
        ArrayList<String> units = HelloApplication.getnames();

        ComboBox cBox = new ComboBox();
        int count = 0;
        for (String s:units){
            cBox.getItems().add(Integer.toString(count)+" "+ s);
            count++;
        }
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label label = new Label("Choose object for change or delete: ");

        Button okButton = new Button("OK");
        okButton.setOnAction(e->{
            if (cBox.getValue()!=null) {

                String[] strChoice = cBox.getValue().toString().split(" ");
                ArrayList<String> paramsTochange = HelloApplication.getParamsToChange( Integer.parseInt(strChoice[0]) );
                change_parametrs(paramsTochange);
            }
            window.close();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{

            window.close();
        });
        layout.getChildren().addAll(label, cBox, okButton, deleteButton);
        Scene scene = new Scene(layout, 303, 303);
        window.setScene(scene);
        window.showAndWait();
    }
}
