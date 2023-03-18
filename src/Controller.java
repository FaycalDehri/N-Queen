import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.URL;
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
    private  ChoiceBox<String> MyChoiceBox;
    @FXML
    private  TextField FieldBoardSize;
    @FXML
    private TextFlow FieldDescription;

    @FXML
    private Label StatT;
    @FXML
    private Label StatM;
    @FXML
    private Label StatG;
    @FXML
    private Label StatD;
    @FXML
    private Label StatS;

    @FXML
    private Label FieldSolutioninDisplay;
    private  String[] choices={"Empty","DFS","BFS","A* Manhattan Distance","A* Euclidean Distance","A* Conflicts Heuristic"};
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

        //MyDescription
        Text text1 = new Text("Select a search Algorithm from the list above");
        FieldDescription.getChildren().addAll(text1);

        //MySolution

        FieldSolutioninDisplay.setText("Empty");

        //Mystats
        StatT.setText("0.00 Seconds");
        StatM.setText(("0.00 Bytes"));
        StatG.setText("0 Nodes");
        StatD.setText("0 Nodes");
        StatS.setText("0 Solutions");

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


    public void SetDescription(String SelectedAlgorithm){
        Text text= new Text("");
        Text Textheuristic1= new Text();
        Text Textheuristic2= new Text();
        switch (SelectedAlgorithm ){
            case "DFS":
                FieldDescription.getChildren().clear();
                text.setText("Depth-First Search (DFS) is an iterative algorithm for exploring all possible solutions to the N-Queen problem. It works by using a stack to keep track of the next queen placement to be explored." +
                        " At each step, the algorithm checks if the current queen placement is valid, and if so, adds the next row to the stack with all possible queen placements. " +
                        "If there are no valid queen placements for a given row, the algorithm backtracks to the previous row and tries the next possible placement." +
                        " DFS can be effective for finding solutions to problems with a relatively small search space.");
                FieldDescription.getChildren().addAll(text);

                ;break;
            case "BFS":
                FieldDescription.getChildren().clear();
                text.setText("BFS is an iterative algorithm that explores all possible solutions to the N-Queen problem by systematically checking each possible queen placement. " +
                        "Unlike DFS, BFS uses a queue to keep track of the next queen placement to be explored. " +
                        "At each step, the algorithm checks all possible queen placements for the current row before moving to the next row." +
                        "This ensures that BFS explores all possible solutions at a given depth before moving to the next depth, making it effective for problems with a relatively large search space." +
                        "However, BFS can be slower than DFS for problems with a small search space due to the overhead of checking all possible placements at each depth.");
                FieldDescription.getChildren().addAll(text);
                ;break;
            case "A* Manhattan Distance":
                FieldDescription.getChildren().clear();
                text.setText("A* is an informed search algorithm that uses heuristics to guide the search towards a solution in an efficient manner. \n");
                Textheuristic1.setText("\nThe Manhattan distance heuristic is a common heuristic used in A* algorithm to guide the search towards the optimal solution efficiently." +
                        "In the N-Queen problem, the heuristic measures the number of squares a queen must move horizontally and vertically to reach its goal position, assuming no other pieces are on the board." +
                        "The heuristic is calculated for each queen on the board, and the sum of all these distances is used to estimate the cost of a given board state.\nThis estimation is then used to prioritize exploring the most promising solutions first.\nThe Manhattan distance heuristic can be effective for problems with a relatively large search space and multiple objectives." );

                FieldDescription.getChildren().addAll(text,Textheuristic1);
                ;break;
            case "A* Euclidean Distance":
                FieldDescription.getChildren().clear();
                text.setText("A* is an informed search algorithm that uses heuristics to guide the search towards a solution in an efficient manner. \n");
                Textheuristic1.setText("\nIn the N-Queen problem, the Euclidean distance heuristic measures the straight-line distance between a queen's current position and its goal position, assuming no other pieces are on the board.");
                Textheuristic2.setText("The heuristic is calculated for each queen on the board, and the sum of all these distances is used to estimate the cost of a given board state. This estimate is then used to prioritize exploring the most promising solutions first.");
                FieldDescription.getChildren().addAll(text,Textheuristic1,Textheuristic2);
                ;break;
            case "A* Conflicts Heuristic":
                FieldDescription.getChildren().clear();
                text.setText("A* is an informed search algorithm that uses heuristics to guide the search towards a solution in an efficient manner. \n");
                Textheuristic1.setText("\nThe conflicts heuristic is another commonly used heuristic in the context of the N-Queen problem. Unlike the Manhattan or Euclidean distance heuristics, the conflicts heuristic does not measure distance directly. Instead, it counts the number of conflicts between pairs of queens on the board. A conflict occurs when two queens threaten each other's positions, either directly or indirectly. By counting the number of conflicts on the board, the heuristic can estimate the cost of a given board state and guide the search towards the most promising solutions. The conflicts heuristic can be effective for problems with a large number of queens, and is often used in combination with other heuristics for even better performance.");
                FieldDescription.getChildren().addAll(text,Textheuristic1);
                ;break;
            default:
                FieldDescription.getChildren().clear();
                text.setText("No algorithm has been selected from the list please try again.");
                FieldDescription.getChildren().addAll(text);
                ;break;
        }

    }

    public void setStats(int n, double time, long memory, int[] SoluArray){
        //Time of execution
        StatT.setText(time +" Seconds");
        //Memory usage
        StatM.setText(memory +" Bytes");
        //Generated Nodes
        StatG.setText(Main.generatedNodes + " Nodes");
        //Developed Nodes
        StatD.setText(Main.developedNodes + " Nodes");
        long numSolutions = 1;
        for (int i = 1; i <= n; i++) {
            numSolutions *= i;
        }
        for (int i = 1; i < n; i++) {
            numSolutions /= i;
            numSolutions *= (n - i);
        }

        //Solutions
        StatS.setText(numSolutions + " Solutions");

        //Display solution
        String solutionArrayToString="";
        for(int i : SoluArray){
           solutionArrayToString= (solutionArrayToString+" "+i);
        }
        FieldSolutioninDisplay.setText(solutionArrayToString);
    }

    public   void SearchMethodSelection(String userSearchMethodChoice, int size , GridPane gridPane, int Choice) throws IOException {
        int userChoice = Choice;
        int[] solutionArr ;
        switch (userSearchMethodChoice) {
            case "DFS": {
                //Search Algorithms Execution
                //DFS
                SetDescription( userSearchMethodChoice);
                // Get the memory bean
                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

                // Get the memory usage before calling the function
                MemoryUsage before = memoryBean.getHeapMemoryUsage();

                //Get the execution Time
                long startTime = System.nanoTime();

                solutionArr = DFS.search(size);

                long endTime = System.nanoTime();
                //Calculate the execution time
                double durationS= (endTime - startTime)/1000000000;
                // Get the memory usage after calling the function
                MemoryUsage after = memoryBean.getHeapMemoryUsage();

                // Calculate the memory used by the function
                long usedMemory = Math.abs(after.getUsed() - before.getUsed());

                Application.queenArrayPlacement(solutionArr, gridPane, size);
                setStats( size,  durationS,  usedMemory, solutionArr);


            } break;
            case "BFS":{
                //Search Algorithms Execution
                //BFS
                SetDescription( userSearchMethodChoice);
                // Get the memory bean
                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

                // Get the memory usage before calling the function
                MemoryUsage before = memoryBean.getHeapMemoryUsage();

                //Get the execution Time
                long startTime = System.nanoTime();
                solutionArr = BFS.search(size);

                long endTime = System.nanoTime();
                //Calculate the execution time
                double durationS= (endTime - startTime)/1000000000;
                // Get the memory usage after calling the function
                MemoryUsage after = memoryBean.getHeapMemoryUsage();

                // Calculate the memory used by the function
                long usedMemory = Math.abs(after.getUsed() - before.getUsed());

                Application.queenArrayPlacement(solutionArr, gridPane, size);
                setStats( size,  durationS,  usedMemory, solutionArr);

            }break;
            case "A* Manhattan Distance":{
                int[] startState = new int[size];

                Node sol1 = new Node(startState,0,size);
                ManhattanDistance h1 = new ManhattanDistance();
                SetDescription( userSearchMethodChoice);
                // Get the memory bean
                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

                // Get the memory usage before calling the function
                MemoryUsage before = memoryBean.getHeapMemoryUsage();

                //Get the execution Time
                long startTime = System.nanoTime();

                solutionArr = Astar.search(sol1,h1);

                long endTime = System.nanoTime();
                //Calculate the execution time
                double durationS= (endTime - startTime)/1000000000;
                // Get the memory usage after calling the function
                MemoryUsage after = memoryBean.getHeapMemoryUsage();

                // Calculate the memory used by the function
                long usedMemory = Math.abs(after.getUsed() - before.getUsed());

                Application.queenArrayPlacement(solutionArr, gridPane, size);
                setStats( size,  durationS,  usedMemory, solutionArr);
            } break;
            case "A* Euclidean Distance":{
                int[] startState = new int[size];

                Node sol2 = new Node(startState,0,size);
                EuclideanDistance h2 = new EuclideanDistance();
                SetDescription( userSearchMethodChoice);
                // Get the memory bean
                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

                // Get the memory usage before calling the function
                MemoryUsage before = memoryBean.getHeapMemoryUsage();

                //Get the execution Time
                long startTime = System.nanoTime();

                solutionArr = Astar.search(sol2,h2);

                long endTime = System.nanoTime();
                //Calculate the execution time
                double durationS= (endTime - startTime)/1000000000;
                // Get the memory usage after calling the function
                MemoryUsage after = memoryBean.getHeapMemoryUsage();

                // Calculate the memory used by the function
                long usedMemory = Math.abs(after.getUsed() - before.getUsed());

                Application.queenArrayPlacement(solutionArr, gridPane, size);
                setStats( size,  durationS,  usedMemory, solutionArr);
            }break;
            case "A* Conflicts Heuristic":{
                int[] startState = new int[size];

                Node sol3 = new Node(startState,0,size);
                ConflictsHeuristic h3 = new ConflictsHeuristic();
                SetDescription( userSearchMethodChoice);
                // Get the memory bean
                MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

                // Get the memory usage before calling the function
                MemoryUsage before = memoryBean.getHeapMemoryUsage();

                //Get the execution Time
                long startTime = System.nanoTime();

                solutionArr = Astar.search(sol3,h3);

                long endTime = System.nanoTime();
                //Calculate the execution time
                double durationS= (endTime - startTime)/1000000000;
                // Get the memory usage after calling the function
                MemoryUsage after = memoryBean.getHeapMemoryUsage();

                // Calculate the memory used by the function
                long usedMemory = Math.abs(after.getUsed() - before.getUsed());

                Application.queenArrayPlacement(solutionArr, gridPane, size);
                setStats( size,  durationS,  usedMemory, solutionArr);
            }break;

            default: {
                SetDescription( userSearchMethodChoice);

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