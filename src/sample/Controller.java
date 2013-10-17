package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.net.*;
import java.io.*;


public class Controller {

    public  Label statusLabel;
    public   Slider  rightSlider;
    public   Slider  leftSlider;
    public   ComboBox  leftCombo;
    public   ComboBox  rightCombo;

    String  rightSpeed="150";
    String  leftSpeed="150";

    boolean pressed=false;

    public void onKeyPress(KeyEvent e) throws Exception {

      if (pressed==false){
        if (e.getCode() == KeyCode.W) {

            move("3,"+leftSpeed+","+rightSpeed,"Pressed " + e.getCode().toString());

        }

        if (e.getCode() == KeyCode.S) {

            move("0,"+leftSpeed+","+rightSpeed,"Pressed " + e.getCode().toString());

        }
        if (e.getCode() == KeyCode.A) {

            move("1,"+leftSpeed+","+rightSpeed,"Pressed " + e.getCode().toString());

        }
        if (e.getCode() == KeyCode.D) {

            move("2,"+leftSpeed+","+rightSpeed,"Pressed " + e.getCode().toString());

        }

         pressed =true;
      }
    }

    public void onKeyRelase(KeyEvent e) throws Exception {

        if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.S ||
                e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
            move("0,0,0","Relased " + e.getCode().toString());
            pressed=false;
        }
    }

    public void onRightSlider()
    {
        rightSpeed = Integer.toString((int)rightSlider.getValue());
        rightCombo.setPromptText(rightSpeed);
        System.out.println(rightSpeed);
    }

    public void onLeftSlider()
    {
        leftSpeed = Integer.toString((int)leftSlider.getValue());
        leftCombo.setPromptText(leftSpeed);
        System.out.println(leftSpeed);

    }


    public void move (String speed,String message) throws Exception
    {
    statusLabel.setText(message);

    URL oracle = new URL("http://192.168.1.104:8080/"+speed);
    URLConnection yc = oracle.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(
            yc.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
    in.close();    }

    public void Stop()  throws Exception
    {

        move("0,0,0","Stop pressed");
    }

    public void moveUp() throws Exception
    {
        move("3,"+leftSpeed+","+rightSpeed,"Up pressed");
    }

    public void moveDown()  throws Exception
    {
            move("0,"+leftSpeed+","+rightSpeed,"Down pressed");
    }

    public void moveLeft()  throws Exception
    {
            move("1,"+leftSpeed+","+rightSpeed,"Left pressed");
    }

    public void moveRight()  throws Exception {

        move("2,"+leftSpeed+","+rightSpeed,"Right pressed");
    }

}
