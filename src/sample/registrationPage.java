package sample;

import Classes.connectivity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class registrationPage implements Initializable {

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXPasswordField passwordConfirmText;

    @FXML
    private JFXTextField lNameText;

    @FXML
    private JFXTextField fNameText;

    @FXML
    private JFXTextField idText;

    @FXML
    private JFXButton createAccBtn;

    @FXML
    private Label loginLbl;

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

    boolean checker=false;

    @Override
    public void initialize(URL location , ResourceBundle resources){
        loginLbl.setOnMouseClicked(e ->{
            try {
                events();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

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

    private void events() throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/loginHomepage.fxml"));
        Parent root = fm.load();

        Stage stage = (Stage) createAccBtn.getScene().getWindow();
        stage.hide();

        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();
    }

    @FXML
    void createAccBtnAction(ActionEvent event) throws SQLException, IOException {
        boolean id=false , pass=false;
        if(checkIfIdExists()){
            /*popupCross("Id already exists.", "", false , false);*/
        }
        else{
            id=true;
        }
        if(checkIfPasswordsMatch()){
            pass=true;
        }
        else{
/*            popupCross("Your passwords don't match, retry.", "", false , false);
 */       }
        if(id ==true && pass==true){
            Statement state = connection.createStatement();
            String query = "INSERT INTO database1.student (idStudent, fNameStudent, lNameStudent, passwordStudent) VALUES ("+idText.getText()+",'"+fNameText.getText()+"','"+lNameText.getText()+"','"+passwordText.getText() +"');";
            state.executeUpdate(query);
          /*  popupTick("Record Inserted Successfully.", "loginHomepage", true , true);
*/

        }

    }

    public boolean checkIfIdExists() throws SQLException {
        ps = connection.prepareStatement("SELECT idStudent FROM database1.student WHERE idstudent = "+idText.getText()+";");
        ResultSet rs = ps.executeQuery();
        boolean checker = false;
        try{
            while(rs.next()){
                if(Integer.parseInt(idText.getText()) == Integer.parseInt(rs.getString("idStudent"))){
                    checker = true;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        this.checker = checker;
        return checker;
    }

    public boolean checkIfPasswordsMatch(){
        if(passwordText.getText().equalsIgnoreCase(passwordConfirmText.getText())){
            return true;
        }
        else {return false;}
    }
/*

    public void popupTick(String text , String fxmlFile, boolean closeWindow, boolean openNewWindow) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/popupTickMarkOneB.fxml"));
        Parent root = fm.load();
        popupTickMarkOneB popup = fm.getController();
        popup.setter(text, fxmlFile ,openNewWindow );
        if(closeWindow){
            Stage stage = (Stage) createAccBtn.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
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
            Stage stage = (Stage) createAccBtn.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
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

}
