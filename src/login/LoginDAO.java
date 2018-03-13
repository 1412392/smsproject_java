/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.AccountInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author vomin
 */
public class LoginDAO {

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    String sql;
    String username;
    String password;
    
    public void Redconfig() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("../config/dbconfig.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
              
                
            }
            String everything = sb.toString();
            
        } finally {
            br.close();
        }
    }
       
    public void setConnection() throws IOException {
        try {
           
            String user = "sa";
            String password ="1";
            String url = "jdbc:sqlserver://localhost;database=SMS";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AccountInfo CheckLogin(String username, String password) throws IOException {
        setConnection();
        AccountInfo cm = new AccountInfo();
        try {
            String SQL = "Select * from Account where Email='" + username + "' AND Password='" + password + "' AND Status=1";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            if (rs != null) {
                while (rs.next()) {

                    cm.Email = rs.getString("Email");
                    cm.Password = rs.getString("Password");
                    cm.Role = rs.getInt("Role");

                }
            } else {

            }
        } catch (Exception e) {
            System.out.println("Error on Building Data");
            return null;
        }
        return cm;
    }
}


