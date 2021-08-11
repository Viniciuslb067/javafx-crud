package controllers;

import database.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import models.Alunos;
import models.Disciplinas;

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
    private Button buttonEnroll;

    private Integer idStudent;

    private String idSubject;

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        insertRecord();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String queryStudent = "SELECT * FROM disciplina";
        String querySubject = "SELECT * FROM alunos";

        Statement st;
        ResultSet rs;


        try {
            ObservableList<Disciplinas> list = getSubjectList();
            ObservableList<Alunos> listStudent = getStudentList();

            choiceBoxSubject.setItems(listStudent);
            choiceBoxSubject.setConverter(new StringConverter<Alunos>() {
                @Override
                public String toString(Alunos object) {
                    return "AB";
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
                    return "ASD";
                }

                @Override
                public Disciplinas fromString(String s) {
                    return choiceBoxStudent.getItems().stream().filter(ap ->
                            ap.getNome().equals(s)).findFirst().orElse(null);
                }
            });

            choiceBoxStudent.valueProperty().addListener((obs, oldval, newval) -> {
                if(newval != null) {

                    idStudent = newval.getId();

                }

            });

            System.out.println(choiceBoxStudent.valueProperty());

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
                disciplinas = new Disciplinas(rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"), rs.getInt("cargaHorario"));
                subjectList.add(disciplinas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
                alunos = new Alunos(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"), rs.getInt("telefone"), rs.getInt("telefonePais"));
                alunoList.add(alunos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alunoList;

    }

    private void insertRecord() throws Exception {

        choiceBoxStudent.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null) {
                String query = "INSERT INTO matricula(idAluno, idDisciplina) VALUES " +
                        "('" + newval.getId() + "','" + choiceBoxSubject.getValue() + "')";

                try {
                    executeQuery(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


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
