import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Main extends javafx.application.Application  {
    int userChoice = 0;
    private static int size;

    private static GridPane gridPane;
    String userSearchMethodChoice ="DFS";


    public int getSize(){
        return size;
    }

    public GridPane getGridPane(){
        return gridPane;
    }
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void start(Stage primaryStage) throws Exception {

        //Creating the gridPane for the ChessBoard
        gridPane = Application.chessBoardCreation();

        //Input size of ChessBoard from user
        size =Application.input();

        //Creating tiles in a gridPane
        Application.tileCreation( gridPane, size);

        // Create the scene
        Group root = new Group(gridPane);
        Scene scene = new Scene(root, size * 100, size * 100);

        //Load Controller Class
        Controller control = new Controller();
        Application.FXMLLoader( root, size);


        // Set the stage
        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public  static void SearchMethodSelection(String userSearchMethodChoice, int size , GridPane gridPane, int Choice) throws IOException {
        int userChoice = Choice;
        Main main = new Main();
        switch (userSearchMethodChoice) {
            case "DFS": {
                //Search Algorithms Execution
                //DFS
                ArrayList<int[]> temp = new ArrayList<int[]>();
                ArrayList<int[]> solution = DFS.search(main.getSize(), temp);

                if (!solution.isEmpty()) {
                    if (solution.size() <= userChoice){
                        System.out.println("Solution ended");
                    }
                    else {
                        int[] node = solution.get(userChoice);
                        System.out.println("Solution in display");
                        System.out.println(size);
                        printArray(node);
                        Application.queenArrayPlacement(node, gridPane, size);
                    }

                } else System.out.println("solution is empty");
            } break;
//            case "BFS":{
//                System.out.println("no bfs yet");
//            }break;
//            case "Heuristic":{
//                System.out.println("no Heuristic yet");
//            } break;
            default: {

            } break;
        }
    }



    public static void main(String[] args) {

        launch();
    }}


