package sample;

import javafx.scene.Group;

public class Cube {
    private final Group cube = new Group();

    public Cube() {
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                for (int k=-1;k<=1;k++){
                    Piece piece = new Piece();
                    piece.getCubits().setLayoutX(i*50);
                    piece.getCubits().setLayoutY(j*50);
                    piece.getCubits().setTranslateZ(k*50);
                    cube.getChildren().add(piece.getCubits());
                }
            }
        }
    }

    public Group getCube() {
        return cube;
    }
}
