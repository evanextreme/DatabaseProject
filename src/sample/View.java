package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;

public class View {

    private BorderPane main = new BorderPane();
    private HBox center = new HBox();

    public View (){

        buildAll();
    }

    public BorderPane getBorderPane(){
        return main;
    }

    public void buildAll(){
        BorderPane border = new BorderPane();

        border.setTop(drawMenu());

        border.setLeft(new VBox());

        border.setCenter(this.center);
        border.setRight(new FlowPane());
        this.main = border;
    }

    private HBox drawMenu(){


        HBox top = new HBox();

        Button registerBtn = new Button();
        registerBtn.setText("Register");
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawRegister();
            }
        });

        Button catalogueBtn = new Button();
        catalogueBtn.setText("Catalogue");
        catalogueBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawCatalogue();
            }
        });


        Button discountsBtn = new Button();
        discountsBtn.setText("Discounts");
        discountsBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawDiscounts();
            }
        });


        Button returnBtn = new Button();
        returnBtn.setText("Return");
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawReturn();
            }
        });

        Button totalBtn = new Button();
        totalBtn.setText("Total");
        totalBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                drawTotal();
            }
        });

        top.getChildren().addAll(registerBtn, catalogueBtn, discountsBtn, returnBtn, totalBtn);
        return top;
    }

    private void drawRegister(){
        center.getChildren().clear();
        Button btn = new Button();
        btn.setText("Register Drawn");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        center.getChildren().add(btn);
    }

    public void drawCatalogue(){
        center.getChildren().clear();
        Button btn = new Button();
        btn.setText("Catalogue Drawn");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        center.getChildren().add(btn);
    }

    public void drawDiscounts(){
        center.getChildren().clear();
        Button btn = new Button();
        btn.setText("Discounts Drawn");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        center.getChildren().add(btn);
    }

    public void drawReturn(){
        center.getChildren().clear();
        Button btn = new Button();
        btn.setText("Returns Drawn");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        center.getChildren().add(btn);
    }

    public void drawTotal(){
        center.getChildren().clear();
        Label title = new Label();
        title.setText("Total");
        title.setFont(Font.font ("Verdana", 20));
        title.setPadding(new Insets(10, 10, 10, 10));
        center.getChildren().addAll(title,drawTotalList());
    }

    private GridPane drawTotalList(){
        GridPane list = new GridPane();
        list.setPadding(new Insets(10));
        list.setGridLinesVisible(true);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(33);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(33);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(33);
        list.getColumnConstraints().addAll(c1, c2, c3);
        list.add(new Label("Name"), 0, 0);
        list.add(new Label("Qty"), 1, 0);
        list.add(new Label("Total"), 2, 0);
        return list;
    }
}
