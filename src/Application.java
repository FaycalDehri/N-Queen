import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Application  {


    public static GridPane createChessBoard(int size,double tileSize) {

        // Create the chess board grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        // Create the tiles and add them to the grid pane
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);
                //tile.setFill(getTileColor(i,j));
                tile.setFill((i + j) % 2 == 0 ? Color.BEIGE : Color.BURLYWOOD);
                gridPane.add(tile, i, j);
            }
        }

        return gridPane;
    }

    public static void FXMLLoader(AnchorPane root, int size) throws IOException {
        // Load the FXML file and get the UI from it
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("/resources/hello-view.fxml"));
        Controller.setBOARD_SIZE(size);
        Parent controllerRoot = loader.load();

    }

    public static Color getTileColor(int row, int col) {
        if ((row + col) % 2 == 0) {
            return Color.BEIGE;
        } else {
            return Color.BURLYWOOD;
        }
    }

    public static void queenPlacement(GridPane gridPane, int size, int i, int j){
        // Load the queen image
        Image BlackqueenImage = new Image(Application.class.getResourceAsStream("resources/images/BlackQueen.png"));
        Image WhitequeenImage = new Image(Application.class.getResourceAsStream("resources/images/WhiteQueen.png"));
        // Create an ImageView for the queen image
        ImageView BlackQueenImageView = new ImageView(BlackqueenImage);
        ImageView WhiteQueenImageView = new ImageView(WhitequeenImage);
        // Set the size of the queen image view to match the size of a tile
        //Black Queen
        if (getTileColor(i,j) == Color.BEIGE){
            BlackQueenImageView.setFitWidth(Controller.TILE_SIZE);
            BlackQueenImageView.setFitHeight(Controller.TILE_SIZE);
            GridPane.setRowIndex(BlackQueenImageView , i);
            GridPane.setColumnIndex(BlackQueenImageView , j);
            gridPane.getChildren().add(BlackQueenImageView);
        }
        else {
            WhiteQueenImageView.setFitWidth(Controller.TILE_SIZE);
            WhiteQueenImageView.setFitHeight(Controller.TILE_SIZE);
            GridPane.setRowIndex(WhiteQueenImageView , i);
            GridPane.setColumnIndex(WhiteQueenImageView , j);
            gridPane.getChildren().add(WhiteQueenImageView);
        }

    }

    public static void queenArrayPlacement (int[] node, GridPane gridPane, int size)
    {

        for ( int i : node)
        {
            queenPlacement( gridPane,  size,  i,  node[i]);
        }
    }

    public static void clearBoard(GridPane gridPane) {

        // Remove all ImageViews representing queens from the grid pane
        gridPane.getChildren().removeIf(child -> child instanceof ImageView && ((ImageView) child).getImage() != null);
    }

    }

