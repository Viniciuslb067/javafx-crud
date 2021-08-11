package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Alunos;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Label label;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfIdade;
    @FXML
    private TableView<Alunos> tvAlunos;
    @FXML
    private TableColumn<Alunos, Integer> colId;
    @FXML
    private TableColumn<Alunos, Integer> colMatricula;
    @FXML
    private TableColumn<Alunos, String> colNome;
    @FXML
    private TableColumn<Alunos, Integer> colIdade;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExcluir;

    private Stage stage;

    private Scene scene;

   private Parent root;

   public void switchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnCadastrar) {
            insertRecord();
        }

        if (event.getSource() == btnExcluir) {
            deleteById();
        }

        if (event.getSource() == btnAtualizar) {
            updateById();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAlunos();
    }

    public Connection getConnection() {
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC", "root", "admin");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error on connection: " + ex.getMessage());
            return null;
        }

    }

    public ObservableList<Alunos> getAlunosList() {
        ObservableList<Alunos> alunoList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM alunos";

        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Alunos alunos;

            while (rs.next()) {
                alunos = new Alunos(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"), rs.getInt("telefone"), rs.getInt("telefonePais") );
                alunoList.add(alunos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alunoList;

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

    public void showAlunos() {
        ObservableList<Alunos> list = getAlunosList();

        colId.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("id"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("matricula"));
        colNome.setCellValueFactory(new PropertyValueFactory<Alunos, String>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<Alunos, Integer>("idade"));

        tvAlunos.setItems(list);

        System.out.println(generateMatricula());

    }

    private void insertRecord() {
        String query = "INSERT INTO alunos(matricula, nome, idade) VALUES ('" + generateMatricula() + "','" + tfNome.getText() + "','" + tfIdade.getText() + "')";
        executeQuery(query);
        showAlunos();
    }

    private void deleteById() {
        String query = "DELETE FROM alunos WHERE id = " + tfId.getText();
        executeQuery(query);
        showAlunos();
    }

    private void updateById() {
        String query = "UPDATE alunos SET matricula = '" + tfMatricula.getText() + "', nome = '" + tfNome.getText() + "', idade = '" + tfIdade.getText() + "' WHERE id = '" + tfId.getText() + "'";
        executeQuery(query);
        showAlunos();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();

        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        Alunos aluno = tvAlunos.getSelectionModel().getSelectedItem();

        tfId.setText("" + aluno.getId());
        tfMatricula.setText("" + aluno.getMatricula());
        tfNome.setText(aluno.getNome());
        tfIdade.setText("" + aluno.getIdade());


    }
}
