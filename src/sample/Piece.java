package sample;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

public class Piece {
    private final Group cubits = new Group();
    private int x;
    private int y;
    private int z;
    private Point3D x_axis = Rotate.X_AXIS;
    private Point3D y_axis = Rotate.Y_AXIS;
    private Point3D z_axis = Rotate.Z_AXIS;

    private Transform t = new Rotate();

    private RotateTransition transition = null;

    public Piece(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;
        Rectangle r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.GREEN);
        cubits.getChildren().add(r);

        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.RED);
        Rotate rot = new Rotate(90,Rotate.Y_AXIS);
        r.getTransforms().add(rot);
        cubits.getChildren().add(r);
        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.ORANGE);
        rot = new Rotate(90,Rotate.Y_AXIS);
        r.getTransforms().add(rot);
        cubits.getChildren().add(r);

        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(-25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.BLUE);
        cubits.getChildren().add(r);

        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.YELLOW);
        rot = new Rotate(-90,Rotate.X_AXIS);
        r.getTransforms().add(rot);
        cubits.getChildren().add(r);
        r = new Rectangle();
        r.setLayoutY(25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-stroke: black");
        r.setFill(Color.WHITE);
        rot = new Rotate(-90,Rotate.X_AXIS);
        r.getTransforms().add(rot);
        cubits.getChildren().add(r);

        cubits.setTranslateX(this.x*50);
        cubits.setTranslateY(this.y*50);
        cubits.setTranslateZ(this.z*50);
    }

    public Group getCubits() {
        return cubits;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getZ() {
        return z;
    }

    public void updatePos(int x,int y,int z){
        System.out.println("x="+this.x +"->"+x);
        System.out.println("y="+this.y +"->"+y);
        System.out.println("z="+this.z +"->"+z);
        System.out.println("==============");
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void translate(){
        cubits.setTranslateX(this.x*50);
        cubits.setTranslateY(this.y*50);
        cubits.setTranslateZ(this.z*50);
    }

    public void rotate(int ang, Point3D axis){
        Rotate r = null;
        if(axis.equals(Rotate.X_AXIS)){
            r = new Rotate(ang,this.x_axis);
            switch(ang){
                case 90:
                    z_axis = Rotate.Y_AXIS;
                    y_axis = new Point3D(0,0,-1);
                    break;
                case -90:
                    y_axis = Rotate.Z_AXIS;
                    z_axis = new Point3D(0,-1,0);
                    break;
            }
        }else if(axis.equals(Rotate.Y_AXIS)){
            r = new Rotate(ang,this.y_axis);
            switch(ang){
                case 90:
                    z_axis = Rotate.X_AXIS;
                    x_axis = new Point3D(0,0,-1);
                    break;
                case -90:
                    x_axis = Rotate.Z_AXIS;
                    z_axis = new Point3D(0,0,-1);
                    break;
            }
        }else if(axis.equals(Rotate.Z_AXIS)){
            r = new Rotate(ang,this.z_axis);
            switch(ang){
                case 90:
                    x_axis = new Point3D(0,-1,0);
                    y_axis = Rotate.X_AXIS;
                    break;
                case -90:
                    x_axis = Rotate.Y_AXIS;
                    y_axis = new Point3D(-1,0,0);
                    break;
            }
        }
        if(r != null){
            t = t.createConcatenation(r);
        }
        cubits.getTransforms().clear();
        cubits.getTransforms().add(t);

    }

    public boolean getStatus(){
        if (transition == null){
            return true;
        }
        return this.transition.getStatus() != Animation.Status.RUNNING;
    }

    public void rotateTransition(int ang, Point3D axis){
        if(transition != null && transition.getStatus().equals(Animation.Status.RUNNING)){
            return;
        }
        double x = cubits.getTranslateX();
        double y= cubits.getTranslateY();
        double z = cubits.getTranslateZ();
        transition = new RotateTransition(Duration.seconds(1),cubits);
        transition.setAxis(axis);
        transition.setByAngle(ang);
        transition.play();
        transition.setOnFinished(event -> {
            System.out.println("X=" + x + "->" +cubits.getTranslateX());
            System.out.println("Y=" + y + "->" +cubits.getTranslateY());
            System.out.println("Z=" + z + "->" +cubits.getTranslateZ());

        });
    }

}
