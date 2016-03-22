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
public class LoginDAOImpl implements LoginDAO {

    @Override
    public User getUser(String emailAddress) {
        User user = null;
        String firstName, lastName, studentType, email, password;
        
        DBHelper.loadDriver();

        try {
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.Users where Email ='"
                    + emailAddress + "'";
            
            System.out.println("LOGIN getUser() string = " + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next()) {
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                studentType = rs.getString("usertype");
                email = rs.getString("email");
                password = rs.getString("password");
                
                
                user = new User(firstName, lastName, studentType, email, password);

            }
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }
    

    @Override
    public boolean authenticate(String email, String pass) {
        DBHelper.loadDriver();

        try {
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.users where email ='"
                    + email
                    + "' and password ='"
                    + pass + "'";
            
            System.out.println("LOGIN authenticate() string = " + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
 
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
