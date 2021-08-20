package controllers;

import database.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Alunos;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterSubjectController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldType;
    @FXML
    private TextField textFieldTime;
    @FXML
    private TextField textFieldPeriod;
    @FXML
    private Button buttonNewSubject;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    public void switchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/index.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchBack() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        Stage window = (Stage) buttonNewSubject.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        insertRecord();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void Information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Disciplina cadastrado com sucesso!");
        alert.showAndWait();
    }

    private void insertRecord() throws Exception {
        String query = "INSERT INTO disciplina(disciplina, tipo, cargaHorario, periodo) VALUES " +
                "('" + textFieldName.getText() + "','" + textFieldType.getText()
                + "','" + textFieldTime.getText() + "','" + textFieldPeriod.getText() + "')";

        System.out.println(query);

        executeQuery(query);
        Information();
        switchBack();
    }

    private void executeQuery(String query) throws Exception {
        Connection conn;
        conn = ConnectionFactory.connectToMySql();

        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
