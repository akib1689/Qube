package sample;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cube {
    private final Group cube = new Group();
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private RotateTransition transition =new RotateTransition();


    public Cube() {
        /*x_.setTranslateX(-150);
        y_.setTranslateY(-150);
        z_.setTranslateZ(-150);*/
        Rotate t = new Rotate(90,Rotate.Y_AXIS);
        Cylinder z_ = new Cylinder(5, 300);
        z_.getTransforms().add(t);
        z_.setMaterial(new PhongMaterial(Color.BLUE));
        t=new Rotate(90,Rotate.X_AXIS);
        Cylinder y_ = new Cylinder(5, 300);
        y_.getTransforms().add(t);
        y_.setMaterial(new PhongMaterial(Color.RED));
        t = new Rotate(90,Rotate.Z_AXIS);
        Cylinder x_ = new Cylinder(5, 300);
        x_.getTransforms().add(t);
        x_.setMaterial(new PhongMaterial(Color.GREEN));
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                for (int k=-1;k<=1;k++){
                    Piece p = new Piece(i,j,k);
                    pieces.add(p);
                    cube.getChildren().add(p.getCubits());
                }
            }
        }
        cube.getChildren().addAll(x_, y_, z_);
    }

    public Group getCube() {
        return cube;
    }

    public void rotate(int angle,int layer,Point3D axis) {
        //angle is 90 or -90
        //layer is -1 0 1
        //axis is either x or y or z axis
        if (transition.getStatus().equals(Animation.Status.RUNNING)){
            return;
        }

        Group grp = new Group();
        for (Piece p :pieces){
            if (axis.equals(Rotate.X_AXIS) && p.getX() == layer){
                cube.getChildren().remove(p.getCubits());
                grp.getChildren().add(p.getCubits());
                int[] newPos = null;
                if (angle == -90){
                    newPos = rotatePos(p.getY(),p.getZ());
                }else if (angle == 90){
                    newPos = rotateNeg(p.getY(),p.getZ());
                }
                if (newPos != null){
                    p.updatePos(p.getX(),newPos[0],newPos[1]);
                }
            }else if (axis.equals(Rotate.Y_AXIS) && p.getY() == layer){
                cube.getChildren().remove(p.getCubits());
                grp.getChildren().add(p.getCubits());
                int [] newPos = null;
                if (angle == 90){
                    newPos = rotatePos(p.getX(),p.getZ());
                }else if (angle ==-90){
                    newPos = rotateNeg(p.getX(),p.getZ());
                }
                if (newPos != null){
                    p.updatePos(newPos[0],p.getY(),newPos[1]);
                }
            }else if(axis.equals(Rotate.Z_AXIS) && p.getZ() == layer){
                cube.getChildren().remove(p.getCubits());
                grp.getChildren().add(p.getCubits());
                int [] newPos = null;
                if (angle == -90){
                    newPos = rotatePos(p.getX(),p.getY());
                }else if (angle == 90){
                    newPos = rotateNeg(p.getX(),p.getY());
                }
                if (newPos != null){
                    p.updatePos(newPos[0],newPos[1],p.getZ());
                }
            }
        }
        cube.getChildren().add(grp);

        transition = new RotateTransition(Duration.seconds(1),grp);
        transition.setByAngle(angle);
        transition.setAxis(axis);
        transition.play();
        transition.setOnFinished(event -> {
            for (Piece p :pieces){
                if(grp.getChildren().remove(p.getCubits())){
                    cube.getChildren().add(p.getCubits());
                    try {
                        p.rotate(angle,axis);
                    } catch (NonInvertibleTransformException e) {
                        e.printStackTrace();
                    }
                    p.translate();
                }
            }
            cube.getChildren().remove(grp);
        });

    }

    private int[] rotatePos(int a,int b){
        int [] ans = new int[2];
        ans[0] = b;
        ans[1] = -a;
        return ans;
    }
    private int[] rotateNeg(int a,int b){
        int [] ans = new int[2];
        ans[0] = -b;
        ans[1] = a;
        return ans;
    }
}
