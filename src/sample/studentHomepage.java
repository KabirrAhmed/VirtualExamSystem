package sample;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import Classes.connectivity;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentHomepage implements Initializable {

    public Label lblWelc;
    public JFXButton signOut;
    @FXML
    private VBox courseList;

    int studentId, quizId;
    boolean checker=false;
    String quizLabel="";

    @Override
    public void initialize(URL location , ResourceBundle resources){

    }


    public void btnCalc(ActionEvent actionEvent) throws IOException, SQLException {
        quizId = 1; quizLabel = "Course: Calculus and Analytical Geometry";
        if(checkIfQuizIsAttemptedBefore())
            popup();
        else
            quiz();
    }

    public void btnDac(ActionEvent actionEvent) throws SQLException, IOException {
        quizId = 2; quizLabel = "Course: Design and Creativity";
        if(checkIfQuizIsAttemptedBefore())
            popup();
        else
            quiz();
    }

    public void btnFop(ActionEvent actionEvent) throws SQLException, IOException {
        quizId = 3; quizLabel = "Course: Fundamentals of Programming";
        if(checkIfQuizIsAttemptedBefore())
            popup();
        else
            quiz();
    }

    public void btnPhy(ActionEvent actionEvent) throws IOException, SQLException {
        quizId = 4; quizLabel = "Course: Applied Physics";
        if(checkIfQuizIsAttemptedBefore())
            popup();
        else
            quiz();
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
        lblWelc.setText("Welcome, " + String.valueOf(studentId));
    }

    public boolean checkIfQuizIsAttemptedBefore() throws SQLException {
        PreparedStatement ps = null;
        connectivity conn = new connectivity();
        Connection connection = conn.db_connection();
        ps = connection.prepareStatement("SELECT quiz_idquiz , student_idstudent FROM database1.result WHERE quiz_idquiz = "+quizId+" AND student_idstudent = "+studentId+";");
        ResultSet rs = ps.executeQuery();
        boolean checker = false;
        try{
            while(rs.next()){
                if(quizId == Integer.parseInt(rs.getString("quiz_idquiz")) && studentId == Integer.parseInt(rs.getString("student_idstudent"))){
                    System.out.println("quiz attempted before");
                    checker = true;
                }
            }
        }
        catch(Exception e){
            try {
                popupCross("Unknown Error Occured" , "", false, false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        System.out.println();
        this.checker = checker;
        return checker;
    }

    public void popup() throws IOException, SQLException {
        /*FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/popupTwoB.fxml"));
        Parent root = fm.load();
        popupTwoB pptb = fm.getController();//Create object of Second class and get its instance by calling method getController
        pptb.setter(quizId , studentId);
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();*/
    }

    public void quiz() throws IOException, SQLException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/quizz.fxml"));
        Parent root = fm.load();
        /*quizz q = fm.getController();//Create object of Second class and get its instance by calling method getController
        q.setQuizId(quizId);
        q.setStudentId(studentId);
        q.setQuizLabel(quizLabel);
        q.getQuestion();*/
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();
    }

    public void signOutAction(ActionEvent actionEvent) throws IOException {
        popupTick("Signed out successfully" , "loginHomepage" , true , true);
    }

    public void popupTick(String text , String fxmlFile, boolean closeWindow, boolean openNewWindow) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/popupTickMarkOneB.fxml"));
        Parent root = fm.load();
        /*popupTickMarkOneB popup = fm.getController();
        popup.setter(text, fxmlFile ,openNewWindow );*/
        if(closeWindow){
            Stage stage = (Stage) signOut.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
            stage.hide();
        }
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();

    }
    public void popupCross(String text , String fxmlFile, boolean closeWindow, boolean openNewWindow) throws IOException {
        /*FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/popupCrossMarkOneB.fxml"));
        Parent root = fm.load();
        popupCrossMarkOneB popup = fm.getController();
        popup.setter(text, fxmlFile ,openNewWindow );
        if(closeWindow){
            Stage stage = (Stage) signOut.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
            stage.hide();
        }
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();*/

    }

    public Circle closeAppBtn;
    public void closeAppBtnOnClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.close();
    }

    public void minimizeBtnOnClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.setIconified(true);
    }

}
