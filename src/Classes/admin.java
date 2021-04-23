package Classes;

import java.sql.SQLException;

public class admin {
    private String password;
    private int id;
    public boolean result;

    public admin() throws SQLException {
    }

    public admin(String password, int id) throws SQLException {
        this.password = password;
        this.id = id;
        login();
    }

    public void login() throws SQLException {
        if (this.id == 123 && this.password.equals("admin")) {
            System.out.println("Login Successful");
            this.result = true;
        }
    }
}