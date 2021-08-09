package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Label labelName;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelCell;
    @FXML
    private Label labelParentsCell;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAlunos();
        int totalRows = 0;

        Connection conn = getConnection();
        String query = "SELECT COUNT(*) AS total FROM alunos";

        Statement st;
        ResultSet rs;

        try {

            st = conn.createStatement();
            rs = st.executeQuery(query);

            if (rs != null & rs.next()) {
                System.out.println(rs.getInt("total"));
                totalRows = rs.getInt("total");
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        Node[] nodes = new Node[totalRows];

        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] =  FXMLLoader.load(getClass().getResource("student.fxml"));
                System.out.println(nodes[i]);
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                System.out.println("ERROR: ");
                e.printStackTrace();
            }
        }



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
                alunos = new Alunos(rs.getInt("id"), rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"));
                alunoList.add(alunos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alunoList;

    }

    public void showAlunos() {
        ObservableList<Alunos> list = getAlunosList();

        labelName.setText(String.valueOf(new PropertyValueFactory<Alunos, String>("nome")));

    }



    private void executeQuery(String query) {
        Connection conn = getConnection();

        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
