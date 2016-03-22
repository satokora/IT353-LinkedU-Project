/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Major;
import model.University;

/**
 *
 * @author skora
 */
public class MajorDAOImpl implements MajorDAO {

    @Override
    public boolean authenticate(String majID, String majName) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.university where major_id='"
                    + majID + "' and major_name='" + majName + "'";
            
            System.out.println("select string =" + selectString);
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
    
    @Override
    public Major getMajor(String majID, String majName)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String mID,mName;
    
        //Arraylist of universities to return.
        Major majEntry = null;
        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.Major where Major_id='" + majID + "' and Major_name='"
                    + majName + "'";
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next())
            {
                mID = rs.getString("major_id");
                mName = rs.getString("major_name");
                
                majEntry = new Major(mID,mName);
            }
            
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return majEntry;
    }
    
    @Override
    public ArrayList<Major> getMajorsOfChoice(String email)
    {    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String majID, majName;
        
        //Arraylist of universities to return.
        ArrayList<Major> majList = new ArrayList<Major>();
        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.MAJOR_OF_CHOICE where Email='" + email + "'";
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next())
            {
                majID = rs.getString("maj_id");
                majName = rs.getString("maj_name");
                
                Major majEntry = getMajor(majID,majName);
                majList.add(majEntry);
            }
            
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return majList;
    }
}
