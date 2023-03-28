package com.example.lab2;

import javafx.scene.input.KeyCode;

public class unit_control {
    public static void Catch(KeyCode Code){
        double x=0.0;
        double y=0.0;
        switch (Code){
            case W -> {
                y -= 10;
            }
            case A -> {
                x -= 10;
            }
            case S -> {
                y += 10;
            }
            case D -> {
                x += 10;
            }
        }
        HelloApplication.moving(x, y);
    }
}
