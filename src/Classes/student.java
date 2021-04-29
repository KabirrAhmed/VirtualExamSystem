package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class student {
    private String fName, lName, password;
    private int id;



    public String getfName() { return fName; }
    public String getlName() { return lName; }
    public String getPassword() { return password; }
    public int getId() { return id; }

    public student() throws SQLException {};

    public student(String password, int id) throws SQLException {
        this.password = password;
        this.id = id;
    }

    public student(int id, String fName, String lName, String password) throws SQLException {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.id = id;
    }
    connectivity conn = new connectivity();
    Connection connection = conn.db_connection();

    public boolean login() throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM studentmanagementsystem.student WHERE idStudent =\""+id+"\" AND passwordStudent = \""+password+"\";");
        ResultSet rs = ps.executeQuery();   //EXECUTES QUERY
        boolean result = false;
        while (rs.next()) {   //WHILE LOOP FETCHES RECORD FROM DATABASE

            int id = rs.getInt("idStudent");
            String password = rs.getString("passwordStudent");
            if(this.id==id && this.password.equals(password)){
                this.fName=rs.getString("first_name");
                this.lName=rs.getString("last_name");
                result = true;}
        }

        if(result){
            return true;}
        else{return false;}
    }
}

