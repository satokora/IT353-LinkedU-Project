/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StudentInfo;
import model.User;

/**
 *
 * @author skora
 */
public class StudentInfoDAOImpl implements StudentInfoDAO
{
    @Override
    public boolean authenticate(String email) {
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
            selectString = "select * from LINKEDU.StudentInfo where email ='"
                    + email + "'";
            
            System.out.println("STUDENTINFO authenticate() string = " + selectString);
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
    public int createStudentInfo(String email, String firstName, String lastName)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "insert into LINKEDU.StudentInfo values('" + email +"', '" + firstName + "', '" + lastName +
                    "', '', '', '', '', '','',NULL,NULL,'','')";
 
            System.out.println("insert string =" + selectString);
            rowCount = stmt.executeUpdate(selectString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;    
    }
    
    @Override
    public StudentInfo getStudentInfo(String emailAdr)
    {
        StudentInfo stuInfo = null;
        
        String email, firstName, lastName, address, city, state, phone, hS,bio, gd,maj;
        
        Blob photo, video;
        
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
            selectString = "select * from LINKEDU.StudentInfo where Email ='"
                    + emailAdr + "'";
            
            System.out.println("STUDENTINFO getStudentInfo() string = " + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next()) {
                email = rs.getString("email");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastName");
                address = rs.getString("address");
                city = rs.getString("city");
                state = rs.getString("state");
                phone = rs.getString("phone");
                hS = rs.getString("highschool");
                photo = rs.getBlob("photo_file");
                video = rs.getBlob("video_file");
                bio = rs.getString("biography");
                gd = rs.getString("grade");
                maj = rs.getString("major");
                
                //File img1 = convertToImage(photo, photoSavePlace + "/" + id + "1.jpg");
                
                
                stuInfo = new StudentInfo(email,firstName,lastName,address,city,state,phone,hS,photo,video,bio,gd,maj);
            }
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stuInfo;
    }
    
    @Override
    public int updateStudentInfo(StudentInfo student) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT353_Fall14_MAGS";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            
            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE LINKEDU.StudentInfo set "
                    + "firstname='" + student.getFirstName()
                    + "', lastname='" + student.getLastName()
                    + "', address='" + student.getAddress()
                    + "', city='" + student.getCity()
                    + "', state='" + student.getState()
                    + "', phone='" + student.getPhone()
                    + "', highschool='" + student.getHighSchool()
                    + "', biography='" + student.getBiography()
                    + "', grade='" + student.getGrade()
                    + "', major='" + student.getMajor()
                    + "' WHERE email='" + student.getEmail()+ "'";
            
            System.out.println("STUDENTINFO updateStudentInfo() string = " + updateString);
            rowCount = stmt.executeUpdate(updateString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;  
    }
    
    public File convertToImage(Blob blob, String newFileName){
        
        File img = null;
        try{
                
                img = new File(newFileName);
                FileOutputStream fos = new FileOutputStream(img);
                
                byte[] buffer;
                buffer = new byte[(int)blob.length()];
                
                InputStream is = blob.getBinaryStream();
                while(is.read(buffer) > 0){
                    fos.write(buffer);
                }
                fos.close();
        
        
        
        }
        catch (FileNotFoundException ex) {
                    Logger.getLogger(StudentInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (SQLException se) {
                    Logger.getLogger(StudentInfoDAOImpl.class.getName()).log(Level.SEVERE, null, se);
         }
        catch (IOException ioe) {
                    Logger.getLogger(StudentInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ioe);
         }
        return img;
    }
}
