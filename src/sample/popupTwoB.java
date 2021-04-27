package sample;

import Classes.connectivity;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class popupTwoB {
    public Label lblStatus;
    public JFXButton btnCancel;
    public JFXButton btnOk;
    boolean status;
    int quizId, studentId;

    public void setLblStatus(Label lblStatus) {
        this.lblStatus = lblStatus;
    }

    public void btnOkAction(ActionEvent actionEvent) throws IOException, SQLException {
        /*deletePreviousRecords();
        quiz();*/
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.hide();
    }

    public void btnCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.hide();
    }

    public void setter(int quizId, int studentId){
        this.quizId = quizId;
        this.studentId = studentId;
    }

    /*public void quiz() throws IOException, SQLException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/quizz.fxml"));
        Parent root = fm.load();
        quizz q = fm.getController();//Create object of Second class and get its instance by calling method getController
        q.setQuizId(quizId);
        q.setStudentId(studentId);
        q.getQuestion();
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();
    }

    public void deletePreviousRecords() throws SQLException {
        PreparedStatement ps = null;
        connectivity conn = new connectivity();
        Connection connection = conn.db_connection();
        Statement state = connection.createStatement();
        //ps = connection.prepareStatement("INSERT INTO database1.result (quizResult , quiz_idquiz, student_idStudent) VALUES ("+score+","+quizId+","+studentId+");");
        String query = "DELETE FROM database1.result WHERE quiz_idquiz = "+quizId+" AND student_idstudent = "+studentId+";";
        state.executeUpdate(query);
    }*/
}
