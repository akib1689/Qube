package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
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
        rotate(45,Rotate.Z_AXIS);
        rotate(45,Rotate.Y_AXIS);
        rotate(45,Rotate.X_AXIS);

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
        }
    }
    private void rotate(int ang, Point3D axis){
        Rotate r = new Rotate(ang,axis);
        t=t.createConcatenation(r);
        cube.getCube().getTransforms().clear();
        cube.getCube().getTransforms().add(t);
    }
   /* private void print(){
        for (int x=0;x<4;x++){
            for (int y=0;y<4;y++){
                for (int z=0;z<4;z++){
                    Transform ta = new Rotate();
                    Rotate xa = new Rotate(x*90,Rotate.X_AXIS);
                    Rotate ya = new Rotate(y*90,Rotate.Y_AXIS);
                    Rotate za = new Rotate(z*90,Rotate.Z_AXIS);

                    ta = ta.createConcatenation(xa);
                    ta = ta.createConcatenation(ya);
                    ta = ta.createConcatenation(za);
                    System.out.println(x*90 +" "+ y*90 + " " +z*90);
                    System.out.println("x->y->z");
                    printTransorm(ta);

                    ta = new Rotate();
                    ta = ta.createConcatenation(xa);
                    ta = ta.createConcatenation(za);
                    ta = ta.createConcatenation(ya);
                    System.out.println(x*90 +" "+ z*90 + " " +y*90);
                    System.out.println("x->z->y");
                    printTransorm(ta);

                    ta = new Rotate();
                    ta = ta.createConcatenation(ya);
                    ta = ta.createConcatenation(za);
                    ta = ta.createConcatenation(xa);
                    System.out.println(y*90 +" "+ z*90 + " " +x*90);
                    System.out.println("y->z->x");
                    printTransorm(ta);

                    ta = new Rotate();
                    ta = ta.createConcatenation(ya);
                    ta = ta.createConcatenation(xa);
                    ta = ta.createConcatenation(za);
                    System.out.println(y*90 +" "+ x*90 + " " +z*90);
                    System.out.println("y->x->z");
                    printTransorm(ta);

                    ta = new Rotate();
                    ta = ta.createConcatenation(za);
                    ta = ta.createConcatenation(xa);
                    ta = ta.createConcatenation(ya);
                    System.out.println(z*90 +" "+ x*90 + " " +y*90);
                    System.out.println("z->x->y");
                    printTransorm(ta);

                    ta = new Rotate();
                    ta = ta.createConcatenation(za);
                    ta = ta.createConcatenation(ya);
                    ta = ta.createConcatenation(xa);
                    System.out.println(z*90 +" "+ y*90 + " " +x*90);
                    System.out.println("z->y->x");
                    printTransorm(ta);
                }
            }
        }

    }

    private void printTransorm(Transform ta) {
        System.out.println(Math.round(ta.getMxx()) + " " + Math.round(ta.getMxy()) + " " + Math.round(ta.getMxz()));
        System.out.println(Math.round(ta.getMyx()) + " " + Math.round(ta.getMyy()) + " " + Math.round(ta.getMyz()));
        System.out.println(Math.round(ta.getMzx()) + " " + Math.round(ta.getMzy()) + " " + Math.round(ta.getMzz()));
        System.out.println("=====================");
    }*/

}