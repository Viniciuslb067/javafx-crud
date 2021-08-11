package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private Button buttonNewStudent;
    @FXML
    private Button buttonNewSubject;
    @FXML
    private Button buttonEnter;
    @FXML
    private Parent root;

    public void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        if (event.getSource() == buttonEnter) {
            root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
            Stage window = (Stage) buttonEnter.getScene().getWindow();
            window.setScene(new Scene(root));
        }

        if (event.getSource() == buttonNewSubject) {
            root = FXMLLoader.load(getClass().getResource("../views/registerSubject.fxml"));
            Stage window = (Stage) buttonNewSubject.getScene().getWindow();
            window.setScene(new Scene(root));
        }

        if (event.getSource() == buttonNewStudent) {
            root = FXMLLoader.load(getClass().getResource("../views/registerStudent.fxml"));
            Stage window = (Stage) buttonNewStudent.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
