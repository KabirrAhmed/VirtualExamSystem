package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teacher {
    private String fName, lName, password;
    private int id;

    public teacher() throws SQLException {
    }

    ;

    public teacher(String password, int id) throws SQLException {
        this.password = password;
        this.id = id;
    }

    public teacher(String fName, String lName, String password, int id) throws SQLException {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.id = id;
    }

    connectivity conn = new connectivity();
    Connection connection = conn.db_connection();

    public boolean login() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM database1.teacher WHERE idTeacher =\"" + id + "\" AND passwordTeacher = \"" + password + "\";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        boolean result = false;
        while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE
            int id = rs.getInt("idTeacher");
            System.out.println(id);
            String password = rs.getString("passwordTeacher");
            System.out.println(password);
            if (this.id == id && this.password.equals(password)) {
                System.out.println("Login Successful");
                result = true;
            }
        }
        if (result) { return true; }
        else { return false; }
    }
}
