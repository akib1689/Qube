package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    @FXML
    private AnchorPane center;




    Transform t=new Rotate();

    Cube cube = new Cube();

    private double anchorX,anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Group p = new Group();
        SubScene scene = new SubScene(p,600,600,true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());
        
        p.getChildren().add(cube.getCube());
        cube.getCube().setOnKeyPressed(event -> {
            keyTyped(event);
        });
        center.getChildren().add(scene);

        initCubeTransform(cube);
        initMouse(cube);

    }

    private void initMouse(Cube cube) {
        Rotate xRotate = new Rotate(0,Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0,Rotate.Y_AXIS);

        cube.getCube().getTransforms().addAll(xRotate,yRotate);

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);



        cube.getCube().setOnMousePressed(event -> {
            cube.focus();
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        cube.getCube().setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }

    private void initCubeTransform(Cube cube){
        cube.getCube().setLayoutX(300);
        cube.getCube().setLayoutY(300);
        cube.getCube().setTranslateZ(-300);
        /*rotate(Rotate.Z_AXIS);
        rotate(Rotate.Y_AXIS);
        rotate(Rotate.X_AXIS);*/
    }


    private void keyTyped(KeyEvent event){
        System.out.println("A key has been pressed");

        if (event.isShiftDown()){
            //
        }else {
            System.out.println("lower case: " + event.getCode().toString());
        }

        /*switch (event.getCode()){

            *//*case Q:
                rotate(10,Rotate.X_AXIS);
                break;
            case E:
                rotate(-10,Rotate.X_AXIS);
                break;
            case A:
                rotate(10,Rotate.Y_AXIS);
                break;
            case D:
                rotate(-10,Rotate.Y_AXIS);
                break;
            case Z:
                rotate(10,Rotate.Z_AXIS);
                break;
            case C:
                rotate(-10,Rotate.Z_AXIS);
                break;
            case NUMPAD1:
                cube.rotate(90,-1,Rotate.X_AXIS);
                break;
            case NUMPAD2:
                cube.rotate(90,0,Rotate.X_AXIS);
                break;
            case NUMPAD3:
                cube.rotate(90,1,Rotate.X_AXIS);
                break;
            case NUMPAD4:
                cube.rotate(90,-1,Rotate.Y_AXIS);
                break;
            case NUMPAD5:
                cube.rotate(90,0,Rotate.Y_AXIS);
                break;
            case NUMPAD6:
                cube.rotate(90,1,Rotate.Y_AXIS);
                break;
            case NUMPAD7:
                cube.rotate(90,-1,Rotate.Z_AXIS);
                break;
            case NUMPAD8:
                cube.rotate(90,0,Rotate.Z_AXIS);
                break;
            case NUMPAD9:
                cube.rotate(90,1,Rotate.Z_AXIS);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getCode());*//*
        }*/
    }
    private void rotate(Point3D axis){
        Rotate r = new Rotate(45,axis);
        t=t.createConcatenation(r);
        cube.getCube().getTransforms().clear();
        cube.getCube().getTransforms().add(t);
    }

}