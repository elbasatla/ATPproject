package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MyView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        Button button = new Button("hi");
        button.setOnAction(e-> System.out.println("primaryStage = [" + primaryStage + "]"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
