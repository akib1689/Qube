package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("qubeui.fxml"));
        Scene scene = new Scene(root,800,600);
        /*Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);*/
        primaryStage.setTitle("Qube");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
