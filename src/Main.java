import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Main extends javafx.application.Application  {
    int userChoice = 0;
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void start(Stage primaryStage) throws Exception {

        //Creating the gridPane for the ChessBoard
        GridPane gridPane = Application.chessBoardCreation();

        //Input size of ChessBoard from user
        int size =Application.input();

        //Creating tiles in a gridPane
        Application.tileCreation( gridPane, size);

        // Create the scene
        Group root = new Group(gridPane);
        Scene scene = new Scene(root, size * 100, size * 100);

        //Load Controller Class
        Application.FXMLLoader( root, size);


        // Set the stage
        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();



        //Search Algorithms Execution
        //DFS
        ArrayList<int[]> temp = new ArrayList<int []>();
        ArrayList<int[]> solution = DFS.search(size, temp);

            if (!solution.isEmpty())
            {

                int[] node = solution.get(userChoice);
                System.out.println("Solution in display");
                printArray(node);
                Application.queenArrayPlacement (node,  gridPane,  size);
            }
            else System.out.println("solution is empty");


//        for(int []i : temp){
//            printArray(i);
//        }
        //Place the queens on the board
//        int row,col;
//        Application.queenPlacement( gridPane, size,5,1);

    }

    public static void main(String[] args) {

        launch();
    }

}
