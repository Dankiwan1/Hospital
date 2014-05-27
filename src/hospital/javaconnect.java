/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dankiwan
 */
public class javaconnect {
 public static Connection ConnectDb(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost/MertmergHospital","root","");
           return con; 
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database Connection Error!!! Check your database connection","Connection..",JOptionPane.ERROR_MESSAGE,null);
            return null;
        }
    }
}
