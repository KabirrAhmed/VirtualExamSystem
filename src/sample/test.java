import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;


class Encryption {

    public String MD5(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while(hashtext.length()<32){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        }catch(Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}

public class test {
    public static void main(String[] args) {
        Encryption ec = new Encryption();
        Connection con;
        try {
            String databaseName = "studentmanagementsystem";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            String username = "root";
            String pass = "";

            con = DriverManager.getConnection(url, username, pass);
            if (con != null) {
                System.out.println("Successful");
                Statement state = con.createStatement();

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT count(idStudent) FROM studentmanagementsystem.student ");
                while ( rs.next() ) {
                    int count = rs.getInt(1);
                    System.out.println(count);
                }
                ResultSet rs2 = stmt.executeQuery("SELECT avg(gpa) FROM studentmanagementsystem.student ");
                while ( rs2.next() ) {
                    float avg = rs2.getFloat(1);
                    System.out.println(avg);
                }
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}