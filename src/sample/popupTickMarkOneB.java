package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class popupTickMarkOneB {

    public JFXButton okBtn;
    public Label popupLbl;
    private boolean closePopup;
    String fxmlFile;

    public void setter(String text, String fxmlFile , boolean openNewWindow){
        popupLbl.setText(text);
        this.fxmlFile = fxmlFile;
        this.closePopup = openNewWindow;
    }

    public void okBtnAction(ActionEvent actionEvent) throws IOException {
        if(closePopup){
            Stage stage = (Stage) okBtn.getScene().getWindow();
            stage.hide();
            FXMLLoader fm = new FXMLLoader(getClass().getResource("../FXMLFiles/"+fxmlFile+".fxml"));
            Parent root = fm.load();
            Stage s =new Stage();
            Scene sc = new Scene(root);
            s.initStyle(StageStyle.UNDECORATED);
            s.hide();
            s.setScene(sc);
            s.show();
        }
        else{
            Stage stage = (Stage) okBtn.getScene().getWindow();
            stage.hide();
        }
    }
}
