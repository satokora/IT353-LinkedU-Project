/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
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

/**
 *
 * @author gmherr2
 */
public class ProfileDAOImpl implements ProfileDAO{

    @Override
    public StudentInfo getProfileInfo(String emailAdr, String photoPath) {
        StudentInfo stuInfo = null;
        
        String email, firstName, lastName, address, city, state, phone, hS, bio;
        
        Blob video;
        File photo;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } 
        catch (ClassNotFoundException e) {
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
                Blob t = rs.getBlob("photo_file");
                photo = convertToImage(t, photoPath + "/" + email + ".png");
                System.out.println("file " + photo.getPath());
                video = rs.getBlob("video_file");
                bio = rs.getString("biography");
                System.out.println(firstName);
                
                stuInfo = new StudentInfo(email,firstName,lastName,address,city,state,phone,hS,photo,video,bio);
            }
            rs.close();
            DBConn.close();
        } 
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stuInfo;
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
                    Logger.getLogger(ProfileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (SQLException se) {
                    Logger.getLogger(ProfileDAOImpl.class.getName()).log(Level.SEVERE, null, se);
         }
        catch (IOException ioe) {
                    Logger.getLogger(ProfileDAOImpl.class.getName()).log(Level.SEVERE, null, ioe);
         }
        return img;
    }
 
    
}
