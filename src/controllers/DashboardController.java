package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Alunos;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    }

    public ObservableList<Alunos> getAlunosList() throws Exception {
        ObservableList<Alunos> alunoList = FXCollections.observableArrayList();
        Connection conn = ConnectionFactory.connectToMySql();
        String query = "SELECT * FROM alunos";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Alunos alunos;

            while (rs.next()) {
                alunos = new Alunos(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"), rs.getInt("telefone"), rs.getInt("telefonePais"));
                alunoList.add(alunos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alunoList;

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
