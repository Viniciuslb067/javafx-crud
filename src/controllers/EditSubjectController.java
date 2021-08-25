package controllers;

import database.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Data;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditSubjectController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldTime;
    @FXML
    private TextField textFieldPeriod;
    @FXML
    private TextField textFieldType;
    @FXML
    private Button btnNewStudent;
    @FXML
    private Button btnDeleteStudent;
    private Parent root;
    private Integer id;

    @FXML
    public void switchBack() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        Stage window = (Stage) btnNewStudent.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        if (event.getSource() == btnNewStudent) {
            updateById();
        }

        if (event.getSource() == btnDeleteStudent) {
            deleteById();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = Data.subjectId;
        textFieldName.setText(Data.subjectName);
        textFieldType.setText(Data.subjectType);
        textFieldTime.setText(String.valueOf(Data.subjectTime));
        textFieldPeriod.setText(String.valueOf(Data.subjectPeriod));
    }

    private void Information() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Aluno atualizado com sucesso!");
        alert.showAndWait();
    }


    private void deleteById() throws Exception {
        String query = "DELETE FROM disciplina WHERE id = " + id;
        executeQuery(query);
        switchBack();
    }

    private void updateById() throws Exception {
        String query = "UPDATE disciplina SET disciplina = '" + textFieldName.getText() +
                "',tipo = '" + textFieldType.getText() + "',cargaHorario = '" + textFieldTime.getText() +
                "',periodo = '" + textFieldPeriod.getText() + "' WHERE id = '" + id + "'";
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
