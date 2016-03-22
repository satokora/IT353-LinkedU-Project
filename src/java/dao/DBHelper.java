/*
 * DBHelper.java
 *
 * Created on November 1, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bllim
 */
public class DBHelper {
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static Connection connect2DB() {
        
        String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
        Connection DBConn = null;
        try {
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return DBConn;
    }
}
