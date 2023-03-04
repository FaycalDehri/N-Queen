import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Application  {

//    private static final int BOARD_SIZE = 8;
    private static int TILE_SIZE = 50;
    public static GridPane chessBoardCreation(){
        // Create the chess board grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        return gridPane;
    }
    public static void tileCreation(GridPane gridPane, int size){
        // Create the tiles and add them to the grid pane
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                //tile.setFill(getTileColor(i,j));
                tile.setFill((i + j) % 2 == 0 ? Color.BEIGE : Color.BURLYWOOD);
                gridPane.add(tile, i, j);
            }
        }

    }



    public static void FXMLLoader(Group root, int size) throws IOException {
        // Load the FXML file and get the UI from it
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("resources/hello-view.fxml"));
        Controller controller = new Controller();
        //loader.setController(controller);
        Controller.setBOARD_SIZE(size);
        Parent controllerRoot = loader.load();
        // Add the UI from the controller to the main scene
        root.getChildren().add(controllerRoot);

    }
    public static int input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int size = scanner.nextInt();
        scanner.close();
        return size;
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
            BlackQueenImageView.setFitWidth(TILE_SIZE);
            BlackQueenImageView.setFitHeight(TILE_SIZE);
            GridPane.setRowIndex(BlackQueenImageView , i);
            GridPane.setColumnIndex(BlackQueenImageView , j);
            gridPane.getChildren().add(BlackQueenImageView);
        }
        else {
            WhiteQueenImageView.setFitWidth(TILE_SIZE);
            WhiteQueenImageView.setFitHeight(TILE_SIZE);
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

