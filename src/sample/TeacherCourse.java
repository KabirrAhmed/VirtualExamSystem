
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


public class TeacherCourse implements Initializable {
    public JFXButton backBtn;
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
        System.out.println("st teacher"+this.teacherId);
        buildData();
    }

    int teacherId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colId;
    public TableColumn<dataModel, Integer> colCourseID;
    public TableColumn<dataModel, String> colCourseTitle;

    static Connection connection = null;
    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colId.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCourseTitle.setCellValueFactory(new PropertyValueFactory<>("course_title"));
        //colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        //colGpa.setCellValueFactory(new PropertyValueFactory<>("Gpa"));
        //add your data to the table here.
        tableView.setItems(dataModels);
        try {
            connection = DriverManager.getConnection(url, username, pass);
            buildData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }/*
        searchButton.setOnMouseClicked(e ->{
            try {
                events1();
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }

        });
*/
    }



    public void buildData(){
        dataModels = FXCollections.observableArrayList();
        //StudentHomepage1 st = new StudentHomepage1();
        //System.out.println(st.getStudentId());
        teacherHomepage th = new teacherHomepage();
        System.out.println(teacherId);
        System.out.println("inside fun"+th.getTeacher_id());
        try{
            //PreparedStatement ps = connection.prepareStatement("SELECT teacher_id,course_id FROM teacher_has_course WHERE teacher_id = "+teacherId+";");
            PreparedStatement ps = connection.prepareStatement("SELECT teacher_has_course.teacher_id,teacher_has_course.course_id, courses.course_title FROM teacher_has_course JOIN courses\n" +
                    "ON (teacher_has_course.course_id = courses.course_id)\n" +
                    "WHERE teacher_id = "+getTeacherId()+";");

            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setTeacherId(Integer.parseInt(rs.getString("teacher_id")));
                dm.setCourseId(Integer.parseInt(rs.getString("course_id")));
                //dm.setGpa(Float.parseFloat(rs.getString("gpa")));
                //dm.setRegDate(rs.getString("registration_date"));
                dm.setCourse_title(rs.getString("course_title"));
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
    private ObservableList<dataModel> dataModels;
    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/teacherHomepage.fxml"));
        Parent root = fm.load();
        teacherHomepage tH = fm.getController();
        tH.setTeacherId(getTeacherId());
        Stage s = new Stage();
        Scene sc = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.hide();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(sc);
        s.setTitle("Welcome, admin");
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
    /*
    public boolean checkIfRecordExists() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT idstudent  FROM studentmanagementsystem.student\n" +
                "WHERE idstudent = "+idText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(rs.getString("idstudent") == idText.getText())
                return true;
        }
        return false;
    }

     */
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
