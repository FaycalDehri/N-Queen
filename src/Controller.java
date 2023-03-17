import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private AnchorPane ContainerLevel1;
    @FXML
    private  Label MyLabel;
    @FXML
    private Label MyStats;
    @FXML
    private Button MyRun;
    @FXML
    private Button MyNext;
    @FXML
    private Button MyPrevious;
    @FXML
    private  ChoiceBox<String> MyChoiceBox;
    @FXML
    private  TextField FieldBoardSize;
    private  String[] choices={"Empty","DFS","BFS","Heuristic"};
    private static int BOARD_SIZE;

    public  AnchorPane ChessBoardAnchor;

    public static void setBOARD_SIZE(int SIZE) {
        BOARD_SIZE = SIZE*50;
    }
    public AnchorPane initContainerLevel1 (){
        double containerWidth = ContainerLevel1.getWidth();
        double containerHeight = ContainerLevel1.getHeight();
        ContainerLevel1.setPrefSize(containerWidth, containerHeight);
        return ContainerLevel1;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //MyChoiceBox methods
        MyChoiceBox.getItems().addAll(choices);
        MyChoiceBox.setValue("Empty");
        MyChoiceBox.setOnAction(this::getChoiceToLabel);
    }
    public void getChoiceToLabel(ActionEvent event) {
        String myChoice = MyChoiceBox.getValue();
        MyLabel.setText(myChoice);
    }

    private int userSolutionChoice = 0;

    private static int MIN_TILE_SIZE = 30;
    private static int MAX_TILE_SIZE = 100;

    private GridPane gridpane;

    @FXML
    private void handleMyDisplayBoardButton(ActionEvent event) throws IOException {
        int boardsize;
        ChessBoardAnchor.getChildren().clear();
        // get Board size from user
        boardsize = Integer.parseInt(FieldBoardSize.getText());
        System.out.println(boardsize);
        if (boardsize < 3) {
            FieldBoardSize.setText("Enter a valid Board size");
        }

        else
            {
                // Determine the size of each tile based on the size of the anchor pane
                double width = ChessBoardAnchor.getPrefWidth();
                double height = ChessBoardAnchor.getPrefHeight();
                double tileSize = Math.min(Math.min(width, height) / boardsize, MAX_TILE_SIZE);
                tileSize = Math.max(tileSize, MIN_TILE_SIZE);
                gridpane = Application.createChessBoard(boardsize,tileSize);
                ChessBoardAnchor.getChildren().add(gridpane);
                ChessBoardAnchor.setTopAnchor(gridpane, 0.0);
                ChessBoardAnchor.setLeftAnchor(gridpane, 0.0);
                ChessBoardAnchor.setRightAnchor(gridpane, 0.0);
                ChessBoardAnchor.setBottomAnchor(gridpane, 0.0);
            }

   }
public static double TILE_SIZE;

    @FXML
    private void handleMyRunButton(ActionEvent event) throws IOException {
        //Create ChessBoard if not created
        int boardsize;
        ChessBoardAnchor.getChildren().clear();
        // get Board size from user
        boardsize = Integer.parseInt(FieldBoardSize.getText());
        System.out.println(boardsize);
        if (boardsize < 3) {
            FieldBoardSize.setText("Enter a valid Board size");
        }

        else
        {
            // Determine the size of each tile based on the size of the anchor pane
            double width = ChessBoardAnchor.getPrefWidth();
            double height = ChessBoardAnchor.getPrefHeight();
            double tileSize = Math.min(Math.min(width, height) / boardsize, MAX_TILE_SIZE);
            tileSize = Math.max(tileSize, MIN_TILE_SIZE);
            TILE_SIZE = tileSize;
            gridpane = Application.createChessBoard(boardsize,tileSize);
            ChessBoardAnchor.getChildren().add(gridpane);
            ChessBoardAnchor.setTopAnchor(gridpane, 0.0);
            ChessBoardAnchor.setLeftAnchor(gridpane, 0.0);
            ChessBoardAnchor.setRightAnchor(gridpane, 0.0);
            ChessBoardAnchor.setBottomAnchor(gridpane, 0.0);
        }
        Application.clearBoard( gridpane);
        System.out.println("button clicked");
        // get the selected search method
        String selectedSearchMethod = MyChoiceBox.getValue();
        System.out.println (MyChoiceBox.getValue().toString());
        // pass the selected search method to the main class
        SearchMethodSelection( selectedSearchMethod,   Integer.parseInt(FieldBoardSize.getText()) ,  gridpane, userSolutionChoice);

        userSolutionChoice ++;
    }



    public  static void SearchMethodSelection(String userSearchMethodChoice, int size , GridPane gridPane, int Choice) throws IOException {
        int userChoice = Choice;

        ArrayList<int[]> SearchSpace = new ArrayList<int[]>();
        ArrayList<int[]> solutionArr ;
        Main main = new Main();
        switch (userSearchMethodChoice) {
            case "DFS": {
                //Search Algorithms Execution
                //DFS

                solutionArr = DFS.search(size, SearchSpace);

                if (!solutionArr.isEmpty()) {
                    if (solutionArr.size() <= userChoice){
                        System.out.println("Solution ended");
                    }
                    else {
                        int[] node = solutionArr.get(userChoice);
                        System.out.println("Solution in display");
                        printArray(node);
                        Application.queenArrayPlacement(node, gridPane, size);
                    }

                } else System.out.println("solution is empty");
            } break;
            case "BFS":{
                //Search Algorithms Execution
                //DFS

                solutionArr = BFS.search(size, SearchSpace);

                if (!solutionArr.isEmpty()) {
                    if (solutionArr.size() <= userChoice){
                        System.out.println("Solution ended");
                    }
                    else {
                        int[] node = solutionArr.get(userChoice);
                        System.out.println("Solution in display");
                        System.out.println(size);
                        printArray(node);
                        Application.queenArrayPlacement(node, gridPane, size);
                    }

                } else System.out.println("solution is empty");
            }break;
//            case "Heuristic":{
//                System.out.println("no Heuristic yet");
//            } break;
            default: {

            } break;
        }
    }

    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}