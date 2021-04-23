package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class teacherHomepage implements Initializable {

    public Label lblWelc;
    public JFXButton fullData;
    public JFXButton signOut;
    public JFXButton btnDacId;
    public JFXButton btnFopId;
    public JFXButton btnPhyId;
    public JFXButton btnCalcId;
    @FXML
    private VBox courseList;

    int studentId, quizId;
    boolean checker = false;
    @Override
    public void initialize(URL location , ResourceBundle resources){

    }

}
