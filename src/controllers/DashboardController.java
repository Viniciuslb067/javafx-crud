package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonStudent;
    @FXML
    private Button buttonSubject;
    @FXML
    private Button buttonEnroll;
    @FXML
    private Button buttonShow;
    @FXML
    private AnchorPane showPane;
    @FXML
    private Label labelTitle;
    @FXML
    private Parent root;

    public void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        if (event.getSource() == buttonHome) {
            root = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
            Stage window = (Stage) buttonHome.getScene().getWindow();
            window.setScene(new Scene(root));
        }

        if (event.getSource() == buttonStudent) {
            labelTitle.setText("Alunos Cadastradas");
            AnchorPane paneStudent = FXMLLoader.load(this.getClass().getResource("../views/tableStudent.fxml"));
            showPane.getChildren().setAll(paneStudent);
        }

        if (event.getSource() == buttonSubject) {
            labelTitle.setText("Disciplinas Cadastradas");
            AnchorPane paneSubject = FXMLLoader.load(this.getClass().getResource("../views/tableSubject.fxml"));
            showPane.getChildren().setAll(paneSubject);
        }

        if (event.getSource() == buttonEnroll) {
            labelTitle.setText("Matricular um aluno");
            AnchorPane paneEnroll = FXMLLoader.load(this.getClass().getResource("../views/enroll.fxml"));
            showPane.getChildren().setAll(paneEnroll);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelTitle.setText("Matriculados");
        AnchorPane paneEnroll = null;
        try {
            paneEnroll = FXMLLoader.load(this.getClass().getResource("../views/tableEnroll.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        showPane.getChildren().setAll(paneEnroll);
    }

}
