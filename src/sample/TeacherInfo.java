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

public class TeacherInfo {

    public Label teacherid;
    public Label f_name;
    public Label l_name;
    public Label datelabel;
    public JFXButton backBtn;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) throws SQLException {
        this.teacherId = teacherId;
        initializeLabels();
        System.out.println("teacher info "+this.teacherId);

    }

    int teacherId;
    connectivity conn = new connectivity();
    Connection connection;
    PreparedStatement ps = null;
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

    public void backOnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/teacherHomepage.fxml"));
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

    public void initializeLabels() throws SQLException {
        System.out.println("Successful2");
        System.out.println(teacherId);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT teacher_id FROM teacher where teacher_id = "+teacherId+";");
        while ( rs.next() ) {
            String teacher_id = rs.getString(1);
            teacherid.setText(teacher_id);
        }
        ResultSet rs2 = stmt.executeQuery("SELECT first_name FROM teacher where teacher_id = "+teacherId+";");
        while ( rs2.next() ) {
            String first_name = rs2.getString(1);
            f_name.setText(String.valueOf(first_name));
        }
        ResultSet rs3 = stmt.executeQuery("SELECT last_name FROM teacher where teacher_id = "+teacherId+";");
        while ( rs3.next() ) {
            String last_name = rs3.getString(1);
            l_name.setText(String.valueOf(last_name));
        }
        ResultSet rs4 = stmt.executeQuery("SELECT registration_date FROM teacher where teacher_id = "+teacherId+";");
        while ( rs4.next() ) {
            String date = rs4.getString(1);
            datelabel.setText(String.valueOf(date));
        }
    }
}
