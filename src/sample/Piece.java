package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Piece {
    private final Group cubits = new Group();

    public Piece() {
        Rectangle r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
        r.setFill(Color.GREEN);
        cubits.getChildren().add(r);

        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
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
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
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
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
        r.setFill(Color.BLUE);
        cubits.getChildren().add(r);

        r = new Rectangle();
        r.setLayoutY(-25);
        r.setLayoutX(-25);
        r.setTranslateZ(25);
        r.setHeight(50);
        r.setWidth(50);
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
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
        r.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 5px");
        r.setFill(Color.WHITE);
        rot = new Rotate(-90,Rotate.X_AXIS);
        r.getTransforms().add(rot);
        cubits.getChildren().add(r);
    }

    public Group getCubits() {
        return cubits;
    }
}
