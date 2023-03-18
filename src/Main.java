import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Main extends javafx.application.Application  {

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static int generatedNodes=0;
    public static int developedNodes=0;
    public static void printArray(int[] t){
        for(int i : t){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {


//        int n = 8;
//        int[] startState = new int[n];
//        int[] solutionArr ;
//
//        Node sol = new Node(startState,0,n);
//        ConflictsHeuristic conflictsHeuristic = new ConflictsHeuristic();
//        EuclideanDistance euclideanDistance = new EuclideanDistance();
//
//
//    long startTime = System.nanoTime();
//
////        solutionArr = DFS.search(n);
////    solutionArr = BFS.search(n);
//        sol.setState(Astar.search(sol,conflictsHeuristic));
////        sol.setState(Astar.search(sol,euclideanDistance));
//
//
//
//    long endTime = System.nanoTime();
//    double durationMS=(endTime - startTime)/1000000;
//    double durationS= durationMS/1000;
//        System.out.println("n =" + n);
//    System.out.println(  durationS);
//    System.out.println(generatedNodes);
//    System.out.println( developedNodes);
//
//        System.out.println(Arrays.toString(sol.getState()));
////    printArray(solutionArr);

        launch();
    }

}


