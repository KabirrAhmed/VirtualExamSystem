package sample;

import Classes.dataModel;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class StudentCourse implements Initializable {

    public JFXButton backBtn;
    int studentId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colCourseID;
    public TableColumn<dataModel, String> colCourseTitle;
    public TableColumn<dataModel, Integer> colGpa;

    static Connection connection = null;
    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "";

    public void setStudentId(int studentId) {
        this.studentId=studentId;
        System.out.println("lets test "+studentId);
        buildData();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCourseTitle.setCellValueFactory(new PropertyValueFactory<>("course_title"));
        colGpa.setCellValueFactory(new PropertyValueFactory<>("Gpa"));
        tableView.setItems(dataModels);
        try {
            connection = DriverManager.getConnection(url, username, pass);
            buildData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void buildData(){
        dataModels = FXCollections.observableArrayList();
        StudentHomepage1 st = new StudentHomepage1();
        System.out.println(st.getStudentId());
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT courses.course_id, courses.course_title, student_has_course.gpa from student_has_course\n" +
                    "join courses on courses.course_id = student_has_course.course_id\n" +
                    "where idStudent = 24\n" +
                    "ORDER BY courses.course_id;");
            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setCourseId(Integer.parseInt(rs.getString("course_id")));
                dm.setCourse_title(rs.getString("course_title"));
                dm.setGpa(Float.parseFloat(rs.getString("gpa")));
                dataModels.add(dm);
            }
            tableView.setItems(dataModels);
            tableView.setOnMouseClicked(e ->{events();});
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                popupCross("Incorrect input try again","",false,false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    public void events1(){
        buildData();
    }

    private ObservableList<dataModel> dataModels;


    public void backBtnAction(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/StudentHomepage1.fxml"));
        Parent root = fm.load();
        StudentHomepage1 stH = fm.getController();
        stH.setStudentId(studentId);
        Stage s = new Stage();
        Scene sc = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.hide();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(sc);
        s.setTitle("Welcome, admin");
        s.show();


    }
    public void popupTick(String text , String fxmlFile, boolean closeWindow, boolean openNewWindow) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/popupTickMarkOneB.fxml"));
        Parent root = fm.load();
        popupTickMarkOneB popup = fm.getController();
        popup.setter(text, fxmlFile ,openNewWindow );
        if(closeWindow){
            Stage stage = (Stage) backBtn.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
            stage.hide();
        }
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();
    }

    public void events(){
        for(dataModel dataModel1 : tableView.getSelectionModel().getSelectedItems()){
            for(int i = 1; i<=1; i++){

                //fNameText.setText(dataModel1.getFirstName());
                //lNameText.setText(dataModel1.getLastName());
                //passwordText.setText(dataModel1.getPassword());
                //regDate.setText(dataModel1.getRegDate());
                //idText.setText(String.valueOf(+dataModel1.getStudentId()));
                //scoreText.setText(String.valueOf(+dataModel1.getGpa()));
            }
        }
    }

    public void popupCross(String text , String fxmlFile, boolean closeWindow, boolean openNewWindow) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/popupCrossMarkOneB.fxml"));
        Parent root = fm.load();
        popupCrossMarkOneB popup = fm.getController();
        popup.setter(text, fxmlFile ,openNewWindow );
        if(closeWindow){
            Stage stage = (Stage) backBtn.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
            stage.hide();
        }
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();

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
