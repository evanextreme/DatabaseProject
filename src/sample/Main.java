package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        View view = new View();
        Scene window = new Scene(view.getBorderPane(), 600, 550);
        primaryStage.setScene(window);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
