import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Main extends javafx.application.Application  {
    int userChoice = 0;
    private static int size;

    private static GridPane gridPane;




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

//        ScrollPane scrollPane = new ScrollPane(); // Create a ScrollPane
//        scrollPane.setContent(gridPane); // Set the content of the ScrollPane to be the GridPane
        //Input size of ChessBoard from user
        size =Application.input();

        //Creating tiles in a gridPane
        Application.tileCreation( gridPane, size);

        // Create the scene
        Controller control = new Controller();

        Group root = new Group(gridPane);
       Scene scene = new Scene(root, size * 100, size * 100);
//        Scene scene = new Scene(control.getContainerLevel1(), control.getContainerWidth(), control.getContainerHeight());
        //Load Controller Class

        Application.FXMLLoader( root, size);


        // Set the stage
        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();



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

                solutionArr = DFS.search(main.getSize(), SearchSpace);

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
            } break;
            case "BFS":{
                //Search Algorithms Execution
                //DFS

                solutionArr = BFS.search(main.getSize(), SearchSpace);

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



    public static void main(String[] args) {

        launch();
    }}


