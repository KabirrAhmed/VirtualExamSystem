package sample;

import Classes.connectivity;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class teacherHomepage {

    public JFXButton InfoBtn;
    public JFXButton EnrollBtn;
    public JFXButton courseBtn;
    public Label labelStudents;
    public Label lblWelc;
    public Label labelFaculty;
    public Label labelCourses;
    public JFXButton backBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    connectivity conn = new connectivity();
    Connection connection;
    PreparedStatement ps = null;
    int teacher_id;

    {
        try {
            connection = conn.db_connection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() throws SQLException {
        initializeLabels();
    }
    public void setTeacherId(int teacher_id){
        this.teacher_id = teacher_id;
        lblWelc.setText("Welcome, " + String.valueOf(teacher_id));

        System.out.println("teacher id issssss"+this.teacher_id);    }
    public int getTeacher_id(){
        return teacher_id;
    }


    public void backOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/loginHomepage.fxml"));
            Parent root = fm.load();
            Stage s = new Stage();
            Scene sc = new Scene(root);
            s.initStyle(StageStyle.UNDECORATED);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.hide();
            s.setScene(sc);
            s.setTitle("Welcome, admin");
            s.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void courseOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/TeacherCourse.fxml"));
            Parent root = fm.load();
            TeacherCourse tc = fm.getController();
            tc.setTeacherId(teacher_id);
            System.out.println("Teacher Id is" +getTeacher_id());
            Stage s =new Stage();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.initStyle(StageStyle.UNDECORATED);
            Stage stage = (Stage) courseBtn.getScene().getWindow();
            stage.hide();

            s.setTitle("Welcome, student.");
            s.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void EnrollOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/TeacherStudent.fxml"));
            Parent root = fm.load();
            TeacherStudent tS = fm.getController();
            tS.setTeacherId(teacher_id);
            Stage s = new Stage();
            Scene sc = new Scene(root);
            s.initStyle(StageStyle.UNDECORATED);
            Stage stage = (Stage) EnrollBtn.getScene().getWindow();
            stage.hide();
            s.setScene(sc);
            s.setTitle("Welcome, student");
            s.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void InfoOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/TeacherInfo.fxml"));
            Parent root = fm.load();
            TeacherInfo tc = fm.getController();
            tc.setTeacherId(teacher_id);
            Stage s = new Stage();
            Scene sc = new Scene(root);
            s.initStyle(StageStyle.UNDECORATED);
            Stage stage = (Stage) InfoBtn.getScene().getWindow();
            stage.hide();
            s.setScene(sc);
            s.setTitle("Welcome, student");
            s.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void initializeLabels() throws SQLException {
        System.out.println("Successful");
        Statement state = connection.createStatement();
/*
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(idStudent) FROM studentmanagementsystem.student ");
        while ( rs.next() ) {
            int count = rs.getInt(1);
            labelStudents.setText(String.valueOf(count));
        }
        ResultSet rs2 = stmt.executeQuery("SELECT count(teacher_id) FROM studentmanagementsystem.teacher ");
        while ( rs2.next() ) {
            int count = rs2.getInt(1);
            labelFaculty.setText(String.valueOf(count));
        }
        ResultSet rs3 = stmt.executeQuery("SELECT count(course_id) FROM studentmanagementsystem.courses ");
        while ( rs3.next() ) {
            int count = rs3.getInt(1);
            labelCourses.setText(String.valueOf(count));
        }

 */
    }
}
