import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label MyLabel;
    @FXML
    private Label MyStats;
    @FXML
    private Button MyRun;

    @FXML
    private ChoiceBox<String> MyChoiceBox;
    private String[] choices={"DFS","BFS","Heuristic"};
    private static int BOARD_SIZE;
    public static void setBOARD_SIZE(int SIZE) {
        BOARD_SIZE = SIZE*50;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Position of MyLable
        MyLabel.setLayoutX(BOARD_SIZE+140);
        MyLabel.setLayoutY(10);
        MyLabel.setAlignment(Pos.CENTER_LEFT);

        //Position of MyChoiceBox
        MyChoiceBox.setLayoutX(BOARD_SIZE+100);
        MyChoiceBox.setLayoutY(MyLabel.getLayoutY() + 40);

        //Position of MyStats
        MyStats.setLayoutY(BOARD_SIZE+25);

        //Position of MyRun
        MyRun.setLayoutX(BOARD_SIZE+140);
        MyRun.setLayoutY(MyChoiceBox.getLayoutY()+40);

        //MyChoiceBox methods
        MyChoiceBox.getItems().addAll(choices);
        MyChoiceBox.setOnAction(this::getChoice);
    }
    public void getChoice (ActionEvent event) {
        String myChoice = MyChoiceBox.getValue();
        MyLabel.setText(myChoice);
    }
}