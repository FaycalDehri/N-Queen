import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
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
    private Button MyNext;
    @FXML
    private Button MyPrevious;
    @FXML
    private  ChoiceBox<String> MyChoiceBox;
    private  String[] choices={"Empty","DFS","BFS","Heuristic"};
    private static int BOARD_SIZE;
    private double containerWidth;
    private double containerHeight;

    public AnchorPane getContainerLevel1() {
        return ContainerLevel1;
    }

    public double getContainerHeight() {
        return containerHeight;
    }

    public double getContainerWidth() {
        return containerWidth;
    }

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

//        //Position of MyLable
//        MyLabel.setLayoutX(BOARD_SIZE+140);
//        MyLabel.setLayoutY(10);
//        MyLabel.setAlignment(Pos.CENTER_LEFT);
//
//        //Position of MyChoiceBox
//        MyChoiceBox.setLayoutX(BOARD_SIZE+100);
//        MyChoiceBox.setLayoutY(MyLabel.getLayoutY() + 40);
//
//
//
//        //Position of MyNext
//        MyNext.setLayoutX(BOARD_SIZE-70);
//        MyNext.setLayoutY(BOARD_SIZE+25);
//
//        //Position of MyPrevious
//        MyPrevious.setLayoutY(BOARD_SIZE+25);
//
//        //Position of MyStats
//        MyStats.setLayoutY(BOARD_SIZE+65);
//
//        //Position of MyRun
//        MyRun.setLayoutX(BOARD_SIZE+140);
//        MyRun.setLayoutY(MyChoiceBox.getLayoutY()+40);
        //Scene Dimensions

        //MyChoiceBox methods
        Controller control1 = new Controller();
        MyChoiceBox.getItems().addAll(choices);
        MyChoiceBox.setValue("Empty");
        MyChoiceBox.setOnAction(this::getChoiceToLabel);
    }
    public void getChoiceToLabel(ActionEvent event) {
        String myChoice = MyChoiceBox.getValue();
        MyLabel.setText(myChoice);
    }

    private int userSolutionChoice = 0;

    @FXML
    private void handleMyRunButton(ActionEvent event) throws IOException {
        Main main = new Main();
        Application.clearBoard( main.getGridPane());
        System.out.println("button clicked");
        // get the selected search method
        String selectedSearchMethod = MyChoiceBox.getValue();
        System.out.println (MyChoiceBox.getValue().toString());
        // pass the selected search method to the main class
        Main.SearchMethodSelection( selectedSearchMethod,  main.getSize() ,  main.getGridPane(), userSolutionChoice);

        userSolutionChoice ++;

    }

}