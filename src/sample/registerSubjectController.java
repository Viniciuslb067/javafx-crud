package sample;

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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registerSubjectController implements Initializable {

    private Label label;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField TfParentsPhone;
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
    private Button btnNewStudent;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExcluir;

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    public void switchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("index.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchBack() throws  IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage window = (Stage) btnNewStudent.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        insertRecord();
    }

    public Connection getConnection() {
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC", "root", "123");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error on connection: " + ex.getMessage());
            return null;
        }

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


    private void insertRecord() throws IOException {
        String query = "INSERT INTO alunos(matricula, nome, idade) VALUES ('" + generateMatricula() + "','" + tfName.getText() + "','" + tfAge.getText() + "')";
        executeQuery(query);
        Information();
        switchBack();
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

}
