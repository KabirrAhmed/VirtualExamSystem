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


public class addTeacher implements Initializable {

    public JFXTextField idText;
    public JFXButton insertData;
    public JFXButton deleteData;
    public JFXButton editData;
    public JFXTextField courseNameText;
    public JFXButton backBtn;


    int quizId = 3, studentId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colId;
    public TableColumn<dataModel, String> colName;

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
        //add your data to the table here.
        tableView.setItems(dataModels);
        try {
            connection = DriverManager.getConnection(url, username, pass);
            buildData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ;
    }
    public void buildData(){
        dataModels = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connection.prepareStatement("Select teacher.teacher_id,teacher.first_name,courses.course_id,courses.course_title\n" +
                    "                    from teacher JOIN teacher_has_course\n" +
                    "                    ON (teacher_has_course.teacher_id = teacher_has_course.teacher_id)\n" +
                    "                    JOIN courses\n" +
                    "                    ON (teacher_has_course.course_id=courses.course_id)\n" +
                    "                     where teacher.teacher_id=205;");
            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setCourseId(Integer.parseInt(rs.getString("course_id")));
                dm.setCourse_title(rs.getString("course_title"));
                dm.setTeacher_id(Integer.parseInt(rs.getString("teacher_id")));
                dm.setTeacherName(rs.getString("first_name"));
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

    }

    public void editDataAction(ActionEvent actionEvent) throws SQLException, IOException {
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
            String query = "DELETE FROM studentmanagementsystem.student_has_course WHERE student_has_course.course_id = "+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
            query = "DELETE FROM studentmanagementsystem.teacher_has_course WHERE teacher_has_course.course_id = "+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
            query = "DELETE FROM studentmanagementsystem.courses WHERE courses.course_id = "+Integer.parseInt(idText.getText())+";";
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
            else{
                Statement state = connection.createStatement();
                String query = " insert into studentmanagementsystem.courses (courses.course_id,courses.course_title) values ("+Integer.parseInt(idText.getText())+",\""+ courseNameText.getText()+"\");";
                state.executeUpdate(query);//EXECUTES QUERY
                try {
                    popupTick("Data Inserted Successfully" , "" , false, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData() throws SQLException, IOException {
        if(checkIfRecordExists()){
            Statement state = connection.createStatement();
            String query = "update studentmanagementsystem.courses set "
                    +"course_title = \""+ courseNameText.getText()+"\" where courses.course_id="+Integer.parseInt(idText.getText())+";";
            state.executeUpdate(query);//EXECUTES QUERY
        }
        else{
            popupCross("Record does not exist.", "", false , false);
        }
    }
    public void events(){
        for(dataModel dataModel1 : tableView.getSelectionModel().getSelectedItems()){
            for(int i = 1; i<=1; i++){
                idText.setText(String.valueOf(dataModel1.getCourse_id()));
                courseNameText.setText(String.valueOf(dataModel1.getTeacher_id()));
            }
        }
    }
    public boolean checkIfRecordExists() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT course_id  FROM studentmanagementsystem.courses\n" +
                "WHERE courses.course_id = "+idText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(Integer.parseInt(rs.getString("course_id")) == Integer.parseInt(idText.getText())){
                System.out.println("Record does exist");
                return true;}
        }
        System.out.println("Record does not exist");
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