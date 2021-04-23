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
import javafx.scene.control.TextField;
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




public class adminStudent implements Initializable {

    public Label courseIdText;
    public JFXTextField idText;
    public JFXTextField scoreText;

    public JFXButton insertData;
    public JFXButton deleteData;
    public JFXButton editData;
    public Label courseText;
    public Label nameText;
    public JFXButton backBtn;

    int quizId = 3, studentId;

    public TableView<dataModel> tableView;
    public TableColumn<dataModel, Integer> colId;
    public TableColumn<dataModel, String> colName;
    public TableColumn<dataModel, Integer> colCourseId;
    public TableColumn<dataModel, String> colCourse;
    public TableColumn<dataModel, Integer> colScore;

    static Connection connection = null;
    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "root123";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*//make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        colId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        //add your data to the table here.
        tableView.setItems(dataModels);
        courseIdText.setText(String.valueOf(quizId));
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
            PreparedStatement ps;
            if(searchText.getText().equalsIgnoreCase("")){
                ps = connection.prepareStatement("SELECT idStudent, fNameStudent, lNameStudent , result.quizResult, course.nameCourse   FROM database1.student\n" +
                        "JOIN database1.result ON database1.student.idStudent = database1.result.student_idStudent\n" +
                        "JOIN database1.quiz ON database1.result.quiz_idquiz = database1.quiz.idquiz\n" +
                        "JOIN database1.course ON database1.quiz.course_idCourse = database1.course.idCourse WHERE result.quiz_idquiz = "+Integer.parseInt(courseIdText.getText())+";");
                //"WHERE idStudent = "+1912350+";");
            }
            else{
                ps = connection.prepareStatement("SELECT idStudent, fNameStudent, lNameStudent , result.quizResult, course.nameCourse   FROM database1.student\n" +
                        "JOIN database1.result ON database1.student.idStudent = database1.result.student_idStudent\n" +
                        "JOIN database1.quiz ON database1.result.quiz_idquiz = database1.quiz.idquiz\n" +
                        "JOIN database1.course ON database1.quiz.course_idCourse = database1.course.idCourse WHERE result.quiz_idquiz = "+Integer.parseInt(courseIdText.getText())+" AND idStudent = "+Integer.parseInt(searchText.getText())+";");
                //"WHERE idStudent = "+1912350+";");
            }
            ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
            while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
                dataModel dm = new dataModel();
                dm.setStudentId(Integer.parseInt(rs.getString("idStudent")));
                dm.setName((rs.getString("fNameStudent") +"  "+ rs.getString("lNameStudent")));
                dm.setCourseName(rs.getString("nameCourse"));
                dm.setScore(Integer.parseInt(rs.getString("quizResult")));
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
*/


    }
    public void insertDataAction(ActionEvent actionEvent) {
        /*deleteData();
        insertData();
        buildData();
        try {
            popupTick("Data Inserted Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void editDataAction(ActionEvent actionEvent) {
        /*deleteData();
        insertData();
        buildData();
        try {
            popupTick("Data Updated Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteDataAction(ActionEvent actionEvent) {
        /*deleteData();
        buildData();
        try {
            popupTick("Data Deleted Successfully" , "" , false, false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void backBtnAction(ActionEvent actionEvent) throws IOException {
        /*FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/teacherHomepage.fxml"));
        Parent root = fm.load();
        Stage s = new Stage();
        Scene sc = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.hide();
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(sc);
        s.setTitle("Welcome, admin");
        s.show();*/
    }
/*
    public void deleteData(){
        try{
            Statement state = connection.createStatement();
            String query = "DELETE FROM `database1`.`result` WHERE quiz_idquiz = "+Integer.parseInt(courseIdText.getText())+" AND student_idStudent = "+Integer.parseInt(idText.getText())+";\n";
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
                    deleteData();
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
                Statement state = connection.createStatement();
                String query = "INSERT INTO `database1`.`result` (`quizResult`, `quiz_idquiz`, `student_idStudent`) VALUES ('"+Integer.parseInt(scoreText.getText())+"','"+Integer.parseInt(courseIdText.getText())+"','"+Integer.parseInt(idText.getText())+"');\n";
                state.executeUpdate(query);//EXECUTES QUERY
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void events(){
        for(dataModel dataModel1 : tableView.getSelectionModel().getSelectedItems()){
            for(int i = 1; i<=1; i++){
                nameText.setText("Name: "+dataModel1.getName());
                idText.setText(String.valueOf(+dataModel1.getStudentId()));
                scoreText.setText(String.valueOf(+dataModel1.getScore()));
            }

        }
    }

    public boolean checkIfRecordExists() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT student_idStudent, quiz_idquiz  FROM database1.result\n" +
                "WHERE student_idStudent = "+idText.getText()+" AND quiz_idquiz="+courseIdText.getText()+";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        while (rs.next()) {
            if(rs.getString("student_idStudent") == idText.getText() && rs.getString("quiz_idquiz") == courseIdText.getText())
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
    */
    public Circle closeAppBtn;
    public void closeAppBtnOnClick(MouseEvent mouseEvent) {
        /*Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.close();*/
    }

    public void minimizeBtnOnClick(MouseEvent mouseEvent) {
        /*Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.setIconified(true);*/
    }


    }
