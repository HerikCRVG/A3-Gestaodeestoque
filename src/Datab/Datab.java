package Datab;

import java.sql.*;

// Classe de conexão com o banco e operação de CRUD 
public class Datab{
    //Constantes de configuração do banco de dados
    private static final String JDBC_DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL        = "jdbc:mysql://localhost:3306/estoque";
    private static final String USER       = "herik";
    private static final String PASS       = "";


    public static Connection getConnection(){
        try {
            Class.forName(JDBC_DRIVE);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt){
        closeConnection(conn);
        if(stmt != null){
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        closeConnection(conn, stmt);
        if(rs != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
