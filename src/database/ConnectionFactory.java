package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";
    private static final String URL = "jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC";

    public static Connection connectToMySql() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection con = connectToMySql();

            if (con != null) {
                System.out.println("Conectado ao banco de dados com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
