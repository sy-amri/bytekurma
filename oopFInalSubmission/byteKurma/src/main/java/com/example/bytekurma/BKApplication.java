//Syahir Amri bin Mohd Azha, 22007728

package com.example.bytekurma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BKApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BKApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Byte Kurma");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("bkLogoType2.png")));
        stage.setScene(scene);
        stage.show();
    }
}