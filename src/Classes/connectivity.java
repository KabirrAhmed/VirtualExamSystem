package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectivity {

    Connection con;

    static String databaseName = "studentmanagementsystem";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    static String username = "root";
    static String pass = "";

    public Connection db_connection() throws SQLException {
        con = DriverManager.getConnection(url , username , pass);
        return con;
    }

}
