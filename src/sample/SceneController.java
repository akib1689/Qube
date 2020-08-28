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
            xAxis = xRotate.transform(Rotate.X_AXIS);
            xAxis = yRotate.transform(Rotate.X_AXIS);
            yAxis = xRotate.transform(Rotate.Y_AXIS);
            yAxis = yRotate.transform(Rotate.Y_AXIS);
            zAxis = xRotate.transform(Rotate.Z_AXIS);
            zAxis = yRotate.transform(Rotate.Z_AXIS);
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
                    flag = getLayer(1,Rotate.X_AXIS);
                    this.cube.rotate(90 * flag,flag,getAxis(Rotate.X_AXIS));
                    break;
                case L:
                    flag = getLayer(1,Rotate.X_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.X_AXIS));
                    break;
                case U:
                    flag = getLayer(1,Rotate.Y_AXIS);
                    this.cube.rotate(90*flag,flag,getAxis(Rotate.Y_AXIS));
                    break;
                case D:
                    flag = getLayer(1,Rotate.Y_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.Y_AXIS));
                    break;
                case B:
                    flag = getLayer(1,Rotate.Z_AXIS);
                    this.cube.rotate(90*flag,flag,getAxis(Rotate.Z_AXIS));
                    break;
                case F:
                    flag = getLayer(1,Rotate.Z_AXIS);
                    this.cube.rotate(90*flag,-1*flag,getAxis(Rotate.Z_AXIS));
                    break;
            }
        }else {
            switch (event.getCode()){
                case L:
                    flag = getLayer(1,Rotate.X_AXIS);
                    this.cube.rotate(-90 * flag,flag,getAxis(Rotate.X_AXIS));
                    break;
                case R:
                    flag = getLayer(1,Rotate.X_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.X_AXIS));
                    break;
                case U:
                    flag = getLayer(1,Rotate.Y_AXIS);
                    this.cube.rotate(-90*flag,flag,getAxis(Rotate.Y_AXIS));
                    break;
                case D:
                    flag = getLayer(1,Rotate.Y_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.Y_AXIS));
                    break;
                case B:
                    flag = getLayer(1,Rotate.Z_AXIS);
                    this.cube.rotate(-90*flag,flag,getAxis(Rotate.Z_AXIS));
                    break;
                case F:
                    flag = getLayer(1,Rotate.Z_AXIS);
                    this.cube.rotate(-90*flag,-1*flag,getAxis(Rotate.Z_AXIS));
                    break;
            }
        }
    }

    private int getLayer(int layer,Point3D axis){
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
            return xFlag? layer: -1*layer;
        }
        if (yAngle < xAngle && yAngle<zAngle){
            return yFlag? layer: -1*layer;
        }

        return zFlag? layer: -1*layer;
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

}