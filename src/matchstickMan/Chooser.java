package matchstickMan;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Chooser extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        display();
    }
    public static void display()
    {
        Stage primaryStage = new Stage();
        try {
            primaryStage.initStyle(StageStyle.TRANSPARENT);
        }finally {

        }
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox(100);
        ImageView imageView = new ImageView("matchstickMan/image/background1.gif");
        ImageView imageView1 = new ImageView("matchstickMan/image/hardBackground.gif");
        imageView.setFitWidth(400);
        imageView.setFitHeight(200);
        imageView1.setFitWidth(400);
        imageView1.setFitHeight(200);
        Rectangle rectangle = new Rectangle(400,200);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.SILVER);
        rectangle.setStrokeWidth(3);
        Rectangle rectangle1 = new Rectangle(400,200);
        rectangle1.setFill(Color.TRANSPARENT);
        rectangle1.setStroke(Color.GOLD);
        rectangle1.setStrokeWidth(3);
        Group group = new Group(imageView,rectangle);
        Group group1 = new Group(imageView1,rectangle1);
        VBox vBox = new VBox(50);
        vBox.setStyle("-fx-background-color: Transparent");
        HBox chooser = new HBox(100);
        chooser.setStyle("-fx-background-color: Transparent");
        Button simple = new Button("Simple");
        simple.setPrefWidth(150);
        simple.setStyle("-fx-background-color: LightGreen; -fx-border-radius: 25;-fx-background-radius: 25;");
        Button hard = new Button("Normal");
        hard.setPrefWidth(150);
        hard.setStyle("-fx-background-color: Yellow; -fx-border-radius: 25;-fx-background-radius: 25;");
        chooser.getChildren().addAll(simple,hard);
        vBox.getChildren().addAll(group, chooser);
        VBox vBox1 = new VBox(50);
        vBox1.setStyle("-fx-background-color: Transparent");
        HBox chooser1 = new HBox(100);
        chooser1.setStyle("-fx-background-color: Transparent");
        Button simple1 = new Button("Hard");
        simple1.setPrefWidth(150);
        simple1.setStyle("-fx-background-color: Orange; -fx-border-radius: 25;-fx-background-radius: 25;");
        Button hard1 = new Button("Fuck");
        hard1.setPrefWidth(150);
        hard1.setStyle("-fx-background-color: Red; -fx-border-radius: 25;-fx-background-radius: 25;");
        chooser1.getChildren().addAll(simple1,hard1);
//        Button PVP1 = new Button("PVP");
//        PVP1.setTextFill(Color.DARKVIOLET);
//        PVP1.setEffect(new Bloom(0.3));
//        PVP1.setStyle("-fx-background-color: Grey");
        vBox1.getChildren().addAll(group1, chooser1);
        Button PVP = new Button("P V P");
        PVP.setPrefWidth(100);
        PVP.setPrefHeight(150);
        PVP.setTextFill(Color.GREY);
        PVP.setEffect(new Bloom(0.3));
        PVP.setStyle("-fx-background-color: RoyalBlue; -fx-font-size: 25; -fx-font-weight: bold");
        hBox.getChildren().addAll(vBox, PVP,vBox1);
        borderPane.setCenter(hBox);
        borderPane.setStyle("-fx-background-color: Transparent");
        Scene scene = new Scene(borderPane);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        /*-----------------------------------------------------------*/
        rectangle.setOnMouseClicked(event ->//normal
        {
            borderPane.getChildren().clear();
            primaryStage.close();
            new Platform().display(primaryStage, "EVE");
        });
        rectangle1.setOnMouseClicked(event ->//hard
        {

            borderPane.getChildren().clear();
            primaryStage.close();
            new Platform().display(primaryStage, "EVE2");
        });
        simple.setOnAction(event ->
        {

            borderPane.getChildren().clear();
            primaryStage.close();
            new Platform().display(primaryStage, "simple");
        });
        hard.setOnAction(event ->
        {
            borderPane.getChildren().clear();
            primaryStage.close();
//            Platform.main(null);
            new Platform().display(primaryStage, "normal");
        });
        simple1.setOnAction(event ->
        {
            borderPane.getChildren().clear();
            primaryStage.close();
//            Platform.main(null);
            new Platform().display(primaryStage, "hard");
        });
        hard1.setOnAction(event ->
        {
            borderPane.getChildren().clear();
            primaryStage.close();
//            Platform.main(null);
            new Platform().display(primaryStage, "fuck");
        });
        PVP.setOnAction(event ->
        {
            borderPane.getChildren().clear();
            primaryStage.close();
            new Platform().display(primaryStage, "PVP");
//            new Platform().display(primaryStage, "PVP");
        });
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
