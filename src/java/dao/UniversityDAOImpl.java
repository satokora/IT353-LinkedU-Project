/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.University;
import model.User;

/**
 *
 * @author skora
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public boolean authenticate(String univID, String univName) {
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
            selectString = "select * from LINKEDU.university where univ_id='"
                    + univID + "' and univ_name='" + univName + "'";
            
            System.out.println("UNIVERSITY authenticate() string = " + selectString);
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
    public University getUniversity(String univID)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String univIdent,universityName,address,city,state,phone,description,url,p1,p2,p3;
        
        File file1,file2,file3;
    
        String showCase;
    
        //Arraylist of universities to return.
        University univEntry = null;
        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.University where Univ_id='" + univID + "'";
            
            System.out.println("UNIVERSITY getUniveristy() string = " + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next())
            {
                univIdent = rs.getString("univ_id");
                universityName = rs.getString("univ_name");
                address = rs.getString("address");
                city = rs.getString("city");
                state = rs.getString("state");
                phone = rs.getString("phone");
                description = rs.getString("description");
                showCase = rs.getString("is_showcase");
                url = rs.getString("url");
                file1 = (File)rs.getObject("file1");
                file2 = (File)rs.getObject("file2");
                file3 = (File)rs.getObject("file3");
                p1 = rs.getString("photoPath1");
                p2 = rs.getString("photoPath2");
                p3 = rs.getString("photoPath3");
                
                boolean convertShowcaseBool = false;
                if(showCase.equalsIgnoreCase("y"))
                {
                    convertShowcaseBool = true;
                }
                else
                {
                    convertShowcaseBool = false;
                }
                
                univEntry = new University(univIdent,universityName,address,city,state,phone,convertShowcaseBool,description,url,file1,file2,file3,p1,p2,p3);
            }
            
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return univEntry;
    }
    
    @Override
    public ArrayList<University> getUniversitiesOfChoice(String email)
    {    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        String univID, univName;
        
        //Arraylist of universities to return.
        ArrayList<University> univList = new ArrayList<University>();
        
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.UNIV_OF_CHOICE where Email='" + email + "'";
            
            System.out.println("UNIVERSITY getUniversitiesOfChoice() string = " + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next())
            {
                univID = rs.getString("univ_id");
                univName = rs.getString("univ_name");
                
                University univEntry = getUniversity(univID);
                univList.add(univEntry);
            }
            
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return univList;
    }
}
