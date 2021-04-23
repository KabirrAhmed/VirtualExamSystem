package sample;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Classes.admin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class adminLogin {

    public JFXPasswordField txtPassword;
    public JFXTextField txtId;
    public JFXButton login;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    public void loginAction(ActionEvent actionEvent) throws SQLException {
        int id = Integer.parseInt(txtId.getText());
        String password = txtPassword.getText();
        admin aadmi = new admin(password, id);
        if(aadmi.result == true){
            try {
                FXMLLoader fm = new FXMLLoader(getClass().getResource("../FxmlFiles/adminHomepage.fxml"));
                Parent root = fm.load();
                Stage s = new Stage();
                Scene sc = new Scene(root);
                s.initStyle(StageStyle.UNDECORATED);
                Stage stage = (Stage) login.getScene().getWindow();
                stage.hide();
                s.setScene(sc);
                s.setTitle("Welcome, admin");
                s.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
