/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

/**
 *
 * @author skora
 */
public class SignUpDAOImpl implements SignUpDAO {
     @Override
    public int updateUser(User user) {
        DBHelper.loadDriver();

        int rowCount = 0;
        try {
            
            Connection DBConn = DBHelper.connect2DB();
            

            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE LINKEDU.Users set "
                    + "firstname='" + user.getFirstName()
                    + "', lastname='" + user.getLastName()
                    + "', email='" + user.getEmailAddress()
                    + "', usertype='" + user.getUserType()
                    + "', password='" + user.getPassword()
                    + "'";
            
            System.out.println("update string =" + updateString);
            rowCount = stmt.executeUpdate(updateString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;  
    }
    @Override
    public int createUser(User user){
        DBHelper.loadDriver();

        int rowCount = 0;
        try {
            Connection DBConn = DBHelper.connect2DB();
            

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO LINKEDU.Users (FIRSTNAME,LASTNAME,USERTYPE, EMAIL, PASSWORD)"
                    + " VALUES ("
                    + "'" + user.getFirstName() 
                    + "','" + user.getLastName()
                    + "','" + user.getUserType()
                    + "','" + user.getEmailAddress()
                    + "','" + user.getPassword()
                    + "')";
            
            System.out.println("insert string =" + insertString);
            rowCount = stmt.executeUpdate(insertString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;        
    }

    @Override
    public boolean exists(String email) {
        DBHelper.loadDriver();

        try {
            
            Connection DBConn = DBHelper.connect2DB();
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "select * from LINKEDU.Users where email ='"
                    + email + "'";
            
            System.out.println("insert string =" + insertString);
            ResultSet rs = stmt.executeQuery(insertString);
            
            if (rs.next()){
                return true;
            }

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


}
