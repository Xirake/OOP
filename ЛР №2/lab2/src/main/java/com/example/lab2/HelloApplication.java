package com.example.lab2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
class Soldier {
    private Label name;

    private double hp;
    private Line life;
    private ImageView isoldier;
    private double x, y;
    private int ID;
    private boolean active;
    private Rectangle rectActive;
    public Soldier(String soldier_name, double h, double _x, double _y){
        x = _x - 80;
        y = _y-70;
        name=new Label(soldier_name);
        name.setLayoutX(x);
        name.setLayoutY(y);
        hp = h;
        life = new Line(x+5, y, x+105, y);
        life.setStrokeWidth(6);
        life.setStroke(Color.GREEN);
        isoldier = new ImageView(HelloApplication.imsoldier);
        isoldier.setX(x);
        isoldier.setY(y);
        ID = HelloApplication.counter;
        HelloApplication.counter++;
        Group combo = new Group();
        combo.getChildren().addAll(name, life, isoldier);
        HelloApplication.group.getChildren().add(combo);


        active= false;

        rectActive= new Rectangle(x-5,y-5,105,105);
        rectActive.setFill(Color.TRANSPARENT);
        rectActive.setStroke(Color.MAGENTA);

    }
    public String getName(){return name.getText();}
    public String getHealth(){return Double.toString(hp);}
    public void setName(String n){name.setText(n);}
    public void setHealth(String h){

        try {
            hp = Double.parseDouble(h);
        }
        catch(Exception e){
            hp=0.0;
        }

        life.setStartX(x);
        life.setStartY(y+15);
        life.setEndX(x+hp);
        life.setEndY(y+15);
    }
    public String getID(){return Integer.toString(ID);}


    public void setCoordinates(){
        name.setLayoutX(x);
        name.setLayoutY(y);


        life.setStartX(x);
        life.setStartY(y+15);
        life.setEndX(x+hp);
        life.setEndY(y+15);
        isoldier.setX(x);
        isoldier.setY(y+15+7);

        rectActive.setX(x-5);
        rectActive.setY(y-5);

    }

    public String getX(){
        return Double.toString(x);
    }

    public void setX( double _x ){
        x= _x;

        setCoordinates();
    }
    public void setX(String sX){
        try {
            x= Double.parseDouble(sX);
        }
        catch(Exception e){
            x=0.0;
        }

        setX(x);
    }
    public String getY(){
        return Double.toString(y);
    }

    public void setY( double _y ){
        y= _y;

        setCoordinates();
    }

    public void setY(String sY) {
        try {
            y = Double.parseDouble(sY);
        } catch (Exception e) {
            y = 0.0;
        }

        setY(y);
    }
    
}



public class HelloApplication extends Application {
    public static ArrayList<String> getParamsToChange(int index){
        Soldier r = herd.get(index);
        ArrayList<String> arr = new ArrayList<>();
        arr.add(r.getID());
        arr.add( r.getName() );
        arr.add( r.getHealth() );
        arr.add( r.getX() );
        arr.add( r.getY() );
        System.out.println(arr);
        return arr;
    }
    public static void changeSoldier(int SoldierIndex, String sName, String sHealth, String sX, String sY ){
        Soldier r= herd.get(SoldierIndex);

        r.setName(sName);
        r.setHealth(sHealth);
        r.setX(sX);
        r.setY(sY);

    }
    public static void moving (double _x, double _y){
        int SoldierIndex = 0;
        Soldier r= herd.get(SoldierIndex);
        r.setX(Double.parseDouble(r.getX())+_x);
        r.setY(Double.parseDouble(r.getY())+_y);
    }
    public static Group group = new Group();
    public static Image imsoldier;
    public static ArrayList<Soldier> herd = new ArrayList<>();
    public static int counter = 0;
    public static ArrayList<String> getnames() {
        ArrayList<String> arr = new ArrayList<>();
        for (Soldier r : HelloApplication.herd) {
            arr.add(r.toString());
        }
        return arr;
    }

    @Override
    public void start(Stage stage) throws IOException {
        imsoldier = new Image("C:\\Users\\koste\\Downloads\\Снимок.png", 140, 200, false, false);
 ;
        Text coordinations = new Text();
        Pane pane = new Pane();
        pane.getChildren().add(coordinations);
        HelloApplication.group.getChildren().add(pane);
        Scene scene = new Scene(group, 1000, 800);
//------------------------------------------------------------------------------------------------------
        //Створення нового об'єкту/зміна параметрів об'єкту
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){

                    ChangeUnitParametrs.display(mouseEvent.getX(), mouseEvent.getY());

                }
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    CreateUnit.display(mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
        //Передача координат на поле
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                coordinations.setText("x: " + mouseEvent.getX() + " y: " + mouseEvent.getY());
                coordinations.setLayoutX(5);
                coordinations.setLayoutY(10);

            }
        });
        //Управління
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                unit_control.Catch(keyEvent.getCode());

            }
        });
//------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}