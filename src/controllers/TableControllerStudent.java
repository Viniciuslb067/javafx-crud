package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Alunos;
import utils.Data;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableControllerStudent implements Initializable {

    @FXML
    private AnchorPane studentPanel;
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
                alunos = new Alunos(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"), rs.getLong("telefone"), rs.getLong("telefonePais"));
                alunoList.add(alunos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alunoList;

    }

    public void showAlunos() throws Exception {
        ObservableList<Alunos> list = getAlunosList();

        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("idade"));
        columnCell.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnParentCell.setCellValueFactory(new PropertyValueFactory<>("telefonePais"));

        tableStudents.setItems(list);

    }

    public void handleClick(MouseEvent mouseEvent) throws IOException {
        Alunos aluno = tableStudents.getSelectionModel().getSelectedItem();

        Data.studentId = aluno.getId();
        System.out.println(aluno.getId());
        Data.studentName = aluno.getNome();
        Data.studentAge = aluno.getIdade();
        Data.studentPhone = aluno.getTelefone();
        Data.studentParentsPhone = aluno.getTelefonePais();

        if (mouseEvent.getClickCount() == 2) {
            AnchorPane panelTwo = FXMLLoader.load(getClass().getResource("../views/editStudent.fxml"));
            studentPanel.getChildren().removeAll();
            studentPanel.getChildren().setAll(panelTwo);
        }
    }
}
