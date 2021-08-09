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

public class indexController implements Initializable {

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
    private Button btnEnter;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnExcluir;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void switchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerSubject.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
