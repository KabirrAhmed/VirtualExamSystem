package sample;

import Classes.dataModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class adminCourse implements Initializable {

    public Label courseIdText;
    public JFXTextField idText;
    public JFXTextField fIdText;

    public JFXButton insertData;
    public JFXButton deleteData;
    public JFXButton editData;
    public JFXTextField courseNameText;
    public JFXButton backBtn;
    public ImageView searchButton;
    public JFXTextField fNameText;

    int quizId = 3, studentId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colId;
    public TableColumn<dataModel, String> colName;
    public TableColumn<dataModel, String> colFName;
    public TableColumn<dataModel, String> colRegDate;
    public TableColumn<dataModel, String> colPassword;
    public TableColumn<dataModel, Integer> colFId;

    static Connection connection = null;
    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("course_title"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        colFId.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        //add your data to the table here.
        tableView.setItems(dataModels);
        try {
            connection = DriverManager.getConnection(url, username, pass);
            buildData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchButton.setOnMouseClicked(e ->{
            try {
                events1();
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
    }



    public void buildData(){
        dataModels = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT courses.course_id,courses.course_title, teacher_has_course.teacher_id, CONCAT(teacher.first_name,' ',teacher.last_name )AS \"Name\"  FROM courses \n" +
                    "\n" +
                    "INNER JOIN teacher_has_course on (courses.course_id = teacher_has_course.course_id)\n" +
                    "INNER JOIN teacher on (teacher.teacher_id = teacher_has_course.teacher_id);");
            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setCourseId(Integer.parseInt(rs.getString("course_id")));
                dm.setCourse_title(rs.getString("course_title"));
                dm.setTeacherId(Integer.parseInt(rs.getString("teacher_id")));
                dm.setTeacherName(rs.getString("Name"));
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


    public void insertDataAction(ActionEvent actionEvent) {
        deleteData();
        insertData();
        buildData();
        try {
            popupTick("Data Inserted Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editDataAction(ActionEvent actionEvent) throws SQLException {
        updateData();
        buildData();
        try {
            popupTick("Data Updated Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataAction(ActionEvent actionEvent) {
        deleteData();
        buildData();
        try {
            popupTick("Data Deleted Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/adminHomepage.fxml"));
        Parent root = fm.load();
        Stage s = new Stage();
        Scene sc = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.hide();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(sc);
        s.setTitle("Welcome, admin");
        s.show();
    }
    public void deleteData(){
        try{
            Statement state = connection.createStatement();
            String query = "DELETE FROM studentmanagementsystem.student_has_course WHERE course_id = "+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
            query = "DELETE FROM studentmanagementsystem.teacher_has_course WHERE course_id = "+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
            query = "DELETE FROM studentmanagementsystem.student WHERE course_id = "+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void insertData(){
        try {
            if(checkIfRecordExists()){
                try{
                    popupCross("Course Record already Exists", "", false, false);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else if(checkIfTeacherExists()){
                try{
                    popupCross("Course Record already Exists", "", false, false);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                Statement state = connection.createStatement();
                String query = " insert into studentmanagementsystem.courses (course_id,course_title) values ("+Integer.parseInt(idText.getText())+",\""+ courseNameText.getText()+"\");";
                state.executeUpdate(query);//EXECUTES QUERY
                query = " insert into studentmanagementsystem.teacher_has_course (teacher_id,course_id) values ("+Integer.parseInt(fIdText.getText())+","+ idText.getText()+");";
                state.executeUpdate(query);//EXECUTES QUERY
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateData() throws SQLException {
        Statement state = connection.createStatement();
        String query = "update studentmanagementsystem.student set "
                +"first_name = '"+ courseNameText.getText()+"', last_name='"+ fNameText.getText()+"', registration_date='"+regDate.getText()+"', passwordStudent = '"+passwordText.getText()+"',gpa="+(fIdText.getText())+" where IdStudent="+Integer.parseInt(idText.getText())+";";
        state.executeUpdate(query);//EXECUTES QUERY
        if(checkIfRecordExists()){

        }
        else{
            System.out.println("NO");
        }
    }

    public void events(){
        for(dataModel dataModel1 : tableView.getSelectionModel().getSelectedItems()){
            for(int i = 1; i<=1; i++){
                courseNameText.setText(dataModel1.getCourse_title());
                fNameText.setText(dataModel1.getTeacherName());
                idText.setText(String.valueOf(dataModel1.getCourse_id()));
                fIdText.setText(String.valueOf(dataModel1.getTeacher_id()));
            }
        }
    }
    public boolean checkIfRecordExists() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT course_id  FROM studentmanagementsystem.courses\n" +
                "WHERE course_id = "+idText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(rs.getString("course_id") == idText.getText())
                return true;
        }
        return false;
    }

    public boolean checkIfTeacherExists() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT teacher_id  FROM studentmanagementsystem.teacher\n" +
                "WHERE teacher_id = "+fIdText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(rs.getString("teacher_id") == idText.getText())
                return true;
        }
        return false;
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
