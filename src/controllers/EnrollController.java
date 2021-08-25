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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Alunos;
import models.Disciplinas;
import models.Matriculas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EnrollController implements Initializable {

    @FXML
    private ChoiceBox<Disciplinas> choiceBoxStudent;
    @FXML
    private ChoiceBox<Alunos> choiceBoxSubject;
    @FXML
    private TableView<Matriculas> tableEnroll;
    @FXML
    private TableColumn<Matriculas, String> columnSubject;
    @FXML
    private TableColumn<Matriculas, String> columnStudent;
    @FXML
    private Button buttonEnroll;
    @FXML
    private Parent root;


    public void handleButtonAction() throws Exception {
        insertRecord();
    }

    @FXML
    public void switchBack() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        Stage window = (Stage) buttonEnroll.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showEnrollment();
            ObservableList<Disciplinas> list = getSubjectList();
            ObservableList<Alunos> listStudent = getStudentList();

            choiceBoxSubject.setItems(listStudent);
            choiceBoxSubject.setConverter(new StringConverter<Alunos>() {
                @Override
                public String toString(Alunos object) {
                    return object.getNome();
                }

                @Override
                public Alunos fromString(String s) {
                    return choiceBoxSubject.getItems().stream().filter(ap ->
                            ap.getNome().equals(s)).findFirst().orElse(null);
                }
            });

            choiceBoxStudent.setItems(list);
            choiceBoxStudent.setConverter(new StringConverter<Disciplinas>() {
                @Override
                public String toString(Disciplinas object) {
                    return object.getNome();
                }

                @Override
                public Disciplinas fromString(String s) {
                    return choiceBoxStudent.getItems().stream().filter(ap ->
                            ap.getNome().equals(s)).findFirst().orElse(null);
                }
            });

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
                disciplinas = new Disciplinas(rs.getInt("id"), rs.getString("disciplina"),
                        rs.getString("tipo"), rs.getInt("cargaHorario"),
                        rs.getInt("periodo"));
                subjectList.add(disciplinas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return subjectList;

    }

    public ObservableList<Alunos> getStudentList() throws Exception {
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
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return alunoList;
    }

    public ObservableList<Matriculas> getEnrollment() throws Exception {
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
                matriculas = new Matriculas(rs.getString("nome"), rs.getString("disciplina"));
                enrollmentList.add(matriculas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return enrollmentList;
    }

    public void showEnrollment() throws Exception {
        ObservableList<Matriculas> list = getEnrollment();
        columnSubject.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("disciplina"));
        columnStudent.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("nome"));

        tableEnroll.setItems(list);
    }

    private void insertRecord() throws Exception {
        String query = "INSERT INTO matricula(id_aluno, id_disciplina) VALUES " +
                "('" + choiceBoxSubject.getValue().getId() + "','" + choiceBoxStudent.getValue().getId() + "')";

        executeQuery(query);
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
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
