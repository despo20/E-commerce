package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class E_commerce extends Application {

  UserInterface userInterface=new UserInterface();
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(userInterface.createContent(),600,600);
        stage.setTitle("welcome shripad!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}