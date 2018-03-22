import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RootController extends Application {

    @FXML private Stage primaryStage;
    @FXML private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
        //showTotalOverview();
    }

    private BorderPane loadController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("layout/RootLayout.fxml"));
            loader.setController(new RootController());
            BorderPane root = loader.load();

            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootController.class.getResource("layout/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            ButtonBar topBar = (ButtonBar) rootLayout.getTop();

            Button totalButton = new Button("Total");
            totalButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    showTotalOverview();
                }
            });

            topBar.getButtons().addAll(totalButton);
            rootLayout.setTop(topBar);
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the total overview inside the root layout.
     */
    public void showTotalOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootController.class.getResource("layout/TotalLayout.fxml"));
            AnchorPane totalOverview = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(totalOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
