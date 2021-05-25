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

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentHomepage1 {

    public JFXButton InfoBtn;
    public JFXButton EnrollBtn;
    public JFXButton courseBtn;
    public Label labelStudents;
    public Label lblWelc;
    public JFXButton backBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    connectivity conn = new connectivity();
    Connection connection;
    PreparedStatement ps = null;
    int studentId;

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

    public void setStudentId(int studentId) throws SQLException {
        this.studentId = studentId;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select CONCAT(student.first_name, ' ', student.last_name)AS Name from student where student.idStudent = "+studentId+";");
        while ( rs.next() ) {
            String studentName = rs.getString(1);
            lblWelc.setText("Welcome, "+studentName);
        }
    }
    public int getStudentId(){
        this.studentId = studentId;
       return studentId;
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

    public void CourseOnClicck(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            course();
        }
    }
    public void courseOnClick(ActionEvent actionEvent) {
        course();
    }

    public void course(){
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/StudentCourse.fxml"));
            Parent root = fm.load();

            Stage s = new Stage();
            StudentCourse stH = fm.getController();//Create object of Second class and get its instance by calling method getController
            stH.setStudentId(studentId);
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.initStyle(StageStyle.UNDECORATED);
            Stage stage = (Stage) courseBtn.getScene().getWindow();
            stage.hide();
            s.setTitle("Welcome, student");
            s.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void EnrollOnClick(ActionEvent actionEvent) {
        enroll();
    }
    public void EnrollOnClicck(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            enroll();
        }
    }

    public void enroll(){
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/studentEnroll.fxml"));
            Parent root = fm.load();
            studentEnroll sE = fm.getController();
            sE.setStudentId(studentId);
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
        info();
    }
    public void InfoOnClicck(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            info();
        }
    }

    public void info(){
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/StudentInfo.fxml"));
            Parent root = fm.load();
            StudentInfo stH = fm.getController();//Create object of Second class and get its instance by calling method getController
            stH.setStudentId(studentId);
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
