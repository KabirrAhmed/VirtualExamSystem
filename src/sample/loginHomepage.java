package sample;

import Classes.popupCrossMarkOneB;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Classes.student;
import Classes.teacher;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginHomepage implements Initializable {


    public Label lblOne;
    public JFXSpinner spinner;
    public JFXPasswordField txtPassword;
    public JFXToggleButton hToggle;
    public JFXTextField txtId;
    public Label lblStatus;
    public JFXButton login;
    public JFXButton registerBtn;
    public Circle closeAppBtn;
    public Circle minimizeBtn;

    boolean chooser = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(hToggle.isSelected())
                    chooser = true;
                else
                    chooser = false;
            }
        });
    }


    public void loginAction(ActionEvent actionEvent) throws SQLException , IOException {
        int id = Integer.parseInt(txtId.getText());
        String password = txtPassword.getText();

        if(chooser){
            teacher t = new teacher(password, id);
            System.out.println(password + id);
            if(t.login()) {
                try {
                    FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/teacherHomepage.fxml"));
                    Parent root = fm.load();
                    Stage s = new Stage();
                    Scene sc = new Scene(root);
                    s.initStyle(StageStyle.UNDECORATED);
                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.hide();
                    s.setScene(sc);
                    s.setTitle("Welcome, teacher");
                    s.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            else{
                popupCross("Invalid ID and/or Password" , "", false, false);
            }

        }

        else if (chooser == false) {
            student st = new student(password, id);
            if(st.login()){
                try {
                    FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/StudentHomepage1.fxml"));
                    Parent root = fm.load();
                    StudentHomepage1 stH = fm.getController();//Create object of Second class and get its instance by calling method getController
                    stH.setStudentId(id);
                    Stage s =new Stage();
                    Scene sc = new Scene(root);
                    s.setScene(sc);
                    s.initStyle(StageStyle.UNDECORATED);
                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.hide();

                    s.setTitle("Welcome, student.");
                    s.show();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else
                popupCross("Invalid ID and/or Password" , "", false, false);
        }
    }

    public void registerBtnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/adminLogin.fxml"));
        Parent root = fm.load();

        Stage stage = (Stage) registerBtn.getScene().getWindow();
        stage.hide();

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
            Stage stage = (Stage) login.getScene().getWindow(); //ONLY CHANGE THIS ID WHENEVER USING IN DIFFERENT SCENES
            stage.hide();
        }
        Stage s =new Stage();
        Scene sc = new Scene(root);
        s.initStyle(StageStyle.UNDECORATED);
        s.hide();
        s.setScene(sc);
        s.show();

    }


    public void closeAppBtnOnClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.close();
    }

    public void minimizeBtnOnClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        stage.setIconified(true);
    }
}
