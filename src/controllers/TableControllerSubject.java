package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Disciplinas;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableControllerSubject implements Initializable {

    @FXML
    private TableView<Disciplinas> tableSubject;
    @FXML
    private TableColumn<Disciplinas, String> columnName;
    @FXML
    private TableColumn<Disciplinas, String> columnType;
    @FXML
    private TableColumn<Disciplinas, Integer> columnTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Disciplinas> getSubjectList() throws Exception {
        ObservableList<Disciplinas> subjectList = FXCollections.observableArrayList();
        Connection conn = ConnectionFactory.connectToMySql();
        String query = "SELECT * FROM disciplina";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Disciplinas disciplinas;

            while (rs.next()) {
                disciplinas = new Disciplinas(rs.getInt("id"), rs.getString("disciplina"), rs.getString("tipo"), rs.getInt("cargaHorario"));
                subjectList.add(disciplinas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return subjectList;

    }

    public void showSubject() throws Exception {
        ObservableList<Disciplinas> list = getSubjectList();

        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("cargaHorario"));

        tableSubject.setItems(list);

    }
}
