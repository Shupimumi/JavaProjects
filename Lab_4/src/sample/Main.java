package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;



public class Main extends Application {
 //Со
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Client-Server App");
        primaryStage.setScene(new Scene(root, 700, 360));
        primaryStage.getIcons().add(new Image("/sample/assets/if__81ui_2303186.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
