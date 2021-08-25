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

public class RegisterStudentController implements Initializable {

    private Label label;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldAge;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private TextField textFieldParentsPhone;
    @FXML
    private Button btnNewStudent;
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
        Stage window = (Stage) btnNewStudent.getScene().getWindow();
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
        alert.setContentText("Aluno cadastrado com sucesso!");
        alert.showAndWait();
    }

    private String generateMatricula() {
        String matricula = "20210800";
        String digito = "1";

        String prefixoString = matricula.substring(0, 6);

        String sufixoString = matricula.substring(matricula.length() - 1);
        int prefixoInt = Integer.parseInt(prefixoString);


        if (sufixoString.equals("9")) {
            sufixoString = "X";
        } else if (sufixoString.equals("X")) {
            sufixoString = "0";
            prefixoInt += 1;
        } else {
            int sufixoInt = Integer.parseInt(sufixoString);
            sufixoString = String.valueOf(sufixoInt + 1);
        }

        String novaMatricula = prefixoInt + digito + sufixoString;

        return novaMatricula;

    }


    private void insertRecord() throws Exception {
        String query = "INSERT INTO alunos(matricula, nome, idade, telefone, telefonePais) VALUES " +
                "('" + generateMatricula() + "','" + textFieldName.getText()
                + "','" + textFieldAge.getText() + "','" + textFieldPhone.getText() + "','" + textFieldParentsPhone.getText() + "')";

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
