/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secure;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Francis Manuel
 */
public class MySqlConnect {
    Connection conn=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?","root","");
            JOptionPane.showMessageDialog(null,"Connected to Database");
            return conn;
        }
    catch(HeadlessException | ClassNotFoundException | SQLException e){
    JOptionPane.showMessageDialog(null,e); 
    return null; 
}
}
}
