package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Alunos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

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
        root = FXMLLoader.load(getClass().getResource("../views/registerSubject.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
