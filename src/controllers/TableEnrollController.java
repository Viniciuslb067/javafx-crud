package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Matriculas;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableEnrollController implements Initializable {

    @FXML
    private TableView<Matriculas> tableEnroll;
    @FXML
    private TableColumn<Matriculas, String> columnSubject;
    @FXML
    private TableColumn<Matriculas, String> columnStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showEnrollment();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Matriculas> getEnrollment() throws  Exception {
        ObservableList<Matriculas> enrollmentList = FXCollections.observableArrayList();
        Connection conn = ConnectionFactory.connectToMySql();
        String query = "SELECT a.nome, d.disciplina FROM alunos AS a INNER JOIN matricula AS m ON (a.id = m.id_aluno) " +
                "INNER JOIN disciplina AS d ON (m.id_disciplina = d.id)";

        Statement st = null;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Matriculas matriculas;

            while (rs.next()) {
                matriculas = new Matriculas(rs.getString("nome"),rs.getString("disciplina"));
                enrollmentList.add(matriculas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return enrollmentList;
    }

    public void showEnrollment() throws Exception {
        ObservableList<Matriculas> list = getEnrollment();

        columnSubject.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tableEnroll.setItems(list);
    }
}
