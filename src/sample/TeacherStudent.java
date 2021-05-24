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


public class TeacherStudent implements Initializable {

    public Label max;
    public JFXTextField idText;
    public JFXTextField scoreText;

    public JFXButton insertData;
    public JFXButton deleteData;
    public JFXButton editData;
    public JFXTextField fNameText;
    public JFXButton backBtn;
    public ImageView searchButton;
    public JFXTextField courseText;
    public JFXTextField regDate;
    public JFXTextField passwordText;

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {

        this.teacherId = teacherId;
        System.out.println("teacher id is : "+teacherId);
        buildData();
    }

    int teacherId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colId;
    public TableColumn<dataModel, String> colFirstName;
    public TableColumn<dataModel, String> colCourse;
    public TableColumn<dataModel, Integer> colGpa;

    static Connection connection = null;
    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course_title"));
        colGpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        //add your data to the table here.
        tableView.setItems(dataModels);
        try {
            connection = DriverManager.getConnection(url, username, pass);
            buildData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchButton.setOnMouseClicked(e -> {
            try {
                events1();
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
    }
   /*SELECT idStudent, max(gpa) FROM student_has_course;
   SELECT idStudent, AVG(gpa) FROM student_has_course;
   public void maxmin(){
       try{
           Statement stmt = connection.createStatement();

           ResultSet rs = stmt.executeQuery("SELECT max(gpa) FROM student_has_course;");
           while ( rs.next() ) {
               int count = rs.getInt(1);
               max.setText(String.valueOf(count));
           }
       }
       catch (SQLException e){
           e.printStackTrace();
       }
    }


    */
    public void buildData(){
        dataModels = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = connection.prepareStatement("Select student_has_course.idStudent,student.first_name,courses.course_title,student_has_course.gpa\n" +
                    "from student JOIN student_has_course\n" +
                    "ON (student.idStudent = student_has_course.idStudent)\n" +
                    "JOIN teacher_has_course\n" +
                    "ON (teacher_has_course.course_id=student_has_course.course_id)\n" +
                    "JOIN courses\n" +
                    "ON (courses.course_id=teacher_has_course.course_id)\n" +
                    "JOIN teacher                \n" +
                    "ON (teacher.teacher_id=teacher_has_course.teacher_id)\n" +
                    "where teacher.teacher_id="+getTeacherId()+";");
            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setStudentId(Integer.parseInt(rs.getString("idStudent")));
                dm.setFirstName(rs.getString("first_name"));
                dm.setCourse_title(rs.getString("course_title"));
                dm.setGpa(rs.getFloat("gpa"));
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


    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/teacherHomepage.fxml"));
        Parent root = fm.load();
        teacherHomepage tH = fm.getController();
        tH.setTeacherId(teacherId);
        Stage s = new Stage();
        Scene sc = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.hide();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(sc);
        s.setTitle("Welcome, admin");
        s.show();
    }


    public void insertData(){
        try {
            if(checkIfRecordExists()){
                try{
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                /*Statement state = connection.createStatement();
                String query = " insert into studentmanagementsystem.student (idStudent,first_name, last_name,gpa, registration_date, passwordStudent) values ("+Integer.parseInt(idText.getText())+",\""+ fNameText.getText()+"\",\""+lNameText.getText()+"\","+(scoreText.getText())+",'"+regDate.getText()+"','"+passwordText.getText()+"');";
                state.executeUpdate(query);//EXECUTES QUERY*/
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateData() throws SQLException {
        Statement state = connection.createStatement();
        String query = "update studentmanagementsystem.student set gpa="+(scoreText.getText())+" where IdStudent="+Integer.parseInt(idText.getText())+";";
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
                fNameText.setText(dataModel1.getFirstName());
                courseText.setText(dataModel1.getCourse_title());
                idText.setText(String.valueOf(+dataModel1.getStudentId()));
                scoreText.setText(String.valueOf(+dataModel1.getGpa()));
            }
        }
    }
    public boolean checkIfRecordExists() throws SQLException {
        /*PreparedStatement ps = connection.prepareStatement("SELECT idstudent  FROM studentmanagementsystem.student\n" +
                "WHERE idstudent = "+idText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(rs.getString("idstudent") == idText.getText())
                return true;
        }*/
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