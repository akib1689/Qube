package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.*;
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
    private Point3D xAxis = Rotate.X_AXIS;
    private Point3D yAxis = Rotate.Y_AXIS;
    private Point3D zAxis = Rotate.Z_AXIS;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Group p = new Group();
        SubScene scene = new SubScene(p,600,600,true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());
        
        p.getChildren().add(cube.getCube());
        cube.getCube().setOnKeyPressed(this::keyTyped);
        center.getChildren().add(scene);

        initCubeTransform(cube);
        initMouse(cube);

    }

    private void initMouse(Cube cube) {

        cube.getCube().setOnMousePressed(event -> {
            cube.focus();
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
        });

        cube.getCube().setOnMouseDragged(event -> {

            double delX = event.getSceneX()-anchorX;
            double delY = event.getSceneY()-anchorY;

            delX = delX/(((Node)event.getSource()).getScene().getWidth())*360;
            delY = delY/(((Node)event.getSource()).getScene().getHeight())*360;

            Rotate rx = new Rotate(delX,Rotate.Y_AXIS);
            Rotate ry = new Rotate(delY,Rotate.X_AXIS);
            t = t.createConcatenation(rx);
            t = t.createConcatenation(ry);
            this.cube.getCube().getTransforms().clear();
            this.cube.getCube().getTransforms().add(t);


            xAxis = t.transform(Rotate.X_AXIS);
            yAxis = t.transform(Rotate.Y_AXIS);
            zAxis = t.transform(Rotate.Z_AXIS);

            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
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
        int flag;
        if (event.isShiftDown()){
            //
            switch (event.getCode()){
                case R:
                    flag = getLayer(Rotate.X_AXIS);
                    this.cube.rotate(90 * flag,flag,getAxis(Rotate.X_AXIS));
                    break;
                case L:
                    flag = getLayer(Rotate.X_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.X_AXIS));
                    break;
                case D:
                    flag = getLayer(Rotate.Y_AXIS);
                    this.cube.rotate(90*flag,flag,getAxis(Rotate.Y_AXIS));
                    break;
                case U:
                    flag = getLayer(Rotate.Y_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.Y_AXIS));
                    break;
                case B:
                    flag = getLayer(Rotate.Z_AXIS);
                    this.cube.rotate(90*flag,flag,getAxis(Rotate.Z_AXIS));
                    break;
                case F:
                    flag = getLayer(Rotate.Z_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.Z_AXIS));
                    break;
            }
        }else {
            switch (event.getCode()){
                case R:
                    flag = getLayer(Rotate.X_AXIS);
                    this.cube.rotate(-90 * flag,flag,getAxis(Rotate.X_AXIS));
                    break;
                case L:
                    flag = getLayer(Rotate.X_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.X_AXIS));
                    break;
                case D:
                    flag = getLayer(Rotate.Y_AXIS);
                    this.cube.rotate(-90*flag,flag,getAxis(Rotate.Y_AXIS));
                    break;
                case U:
                    flag = getLayer(Rotate.Y_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.Y_AXIS));
                    break;
                case B:
                    flag = getLayer(Rotate.Z_AXIS);
                    this.cube.rotate(-90*flag,flag,getAxis(Rotate.Z_AXIS));
                    break;
                case F:
                    flag = getLayer(Rotate.Z_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.Z_AXIS));
                    break;
            }
        }
    }

    private int getLayer(Point3D axis){
        double xAngle = this.xAxis.angle(axis);
        double yAngle = this.yAxis.angle(axis);
        double zAngle = this.zAxis.angle(axis);

        boolean xFlag = true;
        boolean yFlag = true;
        boolean zFlag = true;
        if (xAngle > 90){
            xAngle = 180 - xAngle;
            xFlag = false;
        }

        if (yAngle > 90){
            yAngle = 180 - yAngle;
            yFlag = false;
        }

        if (zAngle > 90){
            zAngle = 180 - zAngle;
            zFlag = false;
        }

        if (xAngle <yAngle && xAngle < zAngle){
            return xFlag? 1 : -1;
        }
        if (yAngle < xAngle && yAngle<zAngle){
            return yFlag? 1 : -1;
        }

        return zFlag? 1 : -1;
    }


    private Point3D getAxis(Point3D axis){
        double xAngle = this.xAxis.angle(axis);
        double yAngle = this.yAxis.angle(axis);
        double zAngle = this.zAxis.angle(axis);

        if (xAngle > 90){
            xAngle = 180 - xAngle;
        }

        if (yAngle > 90){
            yAngle = 180 - yAngle;
        }

        if (zAngle > 90){
            zAngle = 180 - zAngle;
        }

        if (xAngle <yAngle && xAngle < zAngle){
            return Rotate.X_AXIS;
        }
        if (yAngle < xAngle && yAngle<zAngle){
            return Rotate.Y_AXIS;
        }
        return Rotate.Z_AXIS;
    }


    public void scramblePressed(ActionEvent event) {
        cube.scramble();
    }
}