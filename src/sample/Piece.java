package sample;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Piece {
    private final Group cubits = new Group();
    private int x;
    private int y;
    private int z;
    private Point3D x_axis = Rotate.X_AXIS;
    private Point3D y_axis = Rotate.Y_AXIS;
    private Point3D z_axis = Rotate.Z_AXIS;
    private Transform t = new Rotate();
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
        r.setFill(Color.BLUE);
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
        r.setFill(Color.GREEN);
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
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void translate(){
        cubits.setTranslateX(this.x*50);
        cubits.setTranslateY(this.y*50);
        cubits.setTranslateZ(this.z*50);
    }

    public void rotate(int ang, Point3D axis) throws NonInvertibleTransformException {
        Rotate r = null;
        if(axis.equals(Rotate.X_AXIS)){
            r = new Rotate(ang,this.x_axis);
        }else if(axis.equals(Rotate.Y_AXIS)){
            r = new Rotate(ang,this.y_axis);
        }else if(axis.equals(Rotate.Z_AXIS)){
            r = new Rotate(ang,this.z_axis);
        }
        if(r != null){
            t = t.createConcatenation(r);
            Transform fac = r.createInverse();
            x_axis = fac.transform(x_axis);
            z_axis = fac.transform(z_axis);
            y_axis = fac.transform(y_axis);
        }
        cubits.getTransforms().clear();
        cubits.getTransforms().add(t);
    }
}
