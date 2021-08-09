package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import models.Alunos;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TableView<Alunos> tableStudents;
    @FXML
    private TableColumn<Alunos, String> columnName;
    @FXML
    private TableColumn<Alunos, Integer> columnAge;
    @FXML
    private TableColumn<Alunos, Integer> columnCell;
    @FXML
    private TableColumn<Alunos, Integer> columnParentCell;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showAlunos();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void showAlunos() throws Exception {
        ObservableList<Alunos> list = getAlunosList();

        columnName.setCellValueFactory(new PropertyValueFactory<Alunos, String>("nome"));
        columnAge.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("idade"));
        columnCell.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("telefone"));
        columnParentCell.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("telefonePais"));

        tableStudents.setItems(list);

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
