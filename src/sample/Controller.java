package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane center;

    @FXML
    private BorderPane root;

    Transform t=new Rotate();
    Cube cube = new Cube();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        center.setFocusTraversable(true);
        root.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
        cube.getCube().setLayoutX(300);
        cube.getCube().setLayoutY(300);
        cube.getCube().setTranslateZ(-300);
        center.getChildren().add(cube.getCube());
    }


    private void keyPressed(KeyEvent event){
        switch (event.getCode()){
            /*case W:
                p.getCubits().translateZProperty().set(p.getCubits().getTranslateZ()+10);
//                System.out.println(p.faces.getTranslateZ());
                break;
            case S:
                p.getCubits().translateZProperty().set(p.getCubits().getTranslateZ()-10);
//                System.out.println(p.faces.getTranslateZ());
                break;*/
            case Q:
                rotateX(10);
                break;
            case E:
                rotateX(-10);
                break;
            case A:
                rotateY(10);
                break;
            case D:
                rotateY(-10);
                break;
            case Z:
                rotateZ(10);
                break;
            case C:
                rotateZ(-10);
                break;
        }
    }
    private void rotateX(int ang){
        Rotate r = new Rotate(ang,Rotate.X_AXIS);
        t=t.createConcatenation(r);
        cube.getCube().getTransforms().clear();
        cube.getCube().getTransforms().add(t);
    }
    private void rotateY(int ang){
        Rotate r = new Rotate(ang,Rotate.Y_AXIS);
        t=t.createConcatenation(r);
        cube.getCube().getTransforms().clear();
        cube.getCube().getTransforms().add(t);
    }
    private void rotateZ(int ang){
        Rotate r = new Rotate(ang,Rotate.Z_AXIS);
        t=t.createConcatenation(r);
        cube.getCube().getTransforms().clear();
        cube.getCube().getTransforms().add(t);
    }
    
}