import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.text.Text;

public class View {

    private BorderPane main = new BorderPane();
    private VBox center = new VBox();

    public View (){

        buildAll();
    }

    public BorderPane getBorderPane(){
        return main;
    }

    public void buildAll(){
        BorderPane border = new BorderPane();

        border.setTop(drawTopMenu());

        border.setLeft(new VBox());
        border.setCenter(this.center);
        border.setRight(new FlowPane());
        this.main = border;
    }

    private HBox drawTopMenu(){


        HBox topMenu = new HBox();
        topMenu.setPadding(new Insets(15, 12, 15, 12));
        topMenu.setSpacing(10);
        topMenu.setStyle("-fx-background-color: #336699;");

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

        topMenu.getChildren().addAll(registerBtn, catalogueBtn, discountsBtn, returnBtn, totalBtn);
        return topMenu;
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

        HBox viewBox = new HBox();
        viewBox.getChildren().addAll(drawTotalList(), drawTotalSidebar());

        center.getChildren().addAll(title,viewBox);
    }

    private GridPane drawTotalList(){
        GridPane totalList = new GridPane();
        totalList.setPadding(new Insets(10));
        totalList.setGridLinesVisible(true);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(33);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(33);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(33);
        totalList.getColumnConstraints().addAll(c1, c2, c3);
        totalList.add(new Label("Name"), 0, 0);
        totalList.add(new Label("Qty"), 1, 0);
        totalList.add(new Label("Total"), 2, 0);
        return totalList;
    }

    private VBox drawTotalSidebar(){
        VBox totalSidebar = new VBox();
        Button cashBtn = new Button("Cash");
        cashBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cash Clicked");
            }
        });
        Button creditBtn = new Button("Credit");
        creditBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Credit Clicked");
            }
        });
        totalSidebar.getChildren().addAll(cashBtn, creditBtn, drawMoneyBox());
        return totalSidebar;
    }

    private GridPane drawMoneyBox(){
        GridPane moneyBox = new GridPane();
        moneyBox.setStyle("-fx-background-color: #336699;");
        moneyBox.add(new Label("Total    ="), 0, 0);
        moneyBox.add(new Label("Tax      ="), 0, 1);
        moneyBox.add(new Label("Subtotal ="), 0, 2);
        TextField totalField = new TextField("0");
        moneyBox.add(totalField,1,0);
        TextField taxField = new TextField("0");
        //moneyBox.add(taxField, 1, 1);
        TextField subtotalField = new TextField("0");
        //moneyBox.add(subtotalField, 1, 2);
        return moneyBox;
    }
}
