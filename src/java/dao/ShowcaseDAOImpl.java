/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.University;

/**
 *
 * @author skora
 */
public class ShowcaseDAOImpl {
    public ArrayList<University> getFeaturedUniversities(String photoSavePlace){
        DBHelper.loadDriver();
        ArrayList<University> arrUniv = new ArrayList<University>();
        try {
            
            Connection DBConn = DBHelper.connect2DB();
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "select * from LINKEDU.University where IS_SHOWCASE ='y'";
            
            System.out.println("SHOWCASE getFeaturedUniversities() string = " + insertString);
            ResultSet rs = stmt.executeQuery(insertString);
            
            while (rs.next()){
                String id = rs.getString("UNIV_ID");
                String name = rs.getString("UNIV_NAME");
                String address = rs.getString("ADDRESS");
                String city = rs.getString("CITY");
                String state = rs.getString("STATE");
                String phone = rs.getString("PHONE");
                String isShowCase = rs.getString("IS_SHOWCASE");
                String desc = rs.getString("DESCRIPTION");
                String url = rs.getString("URL");
                

                
                Blob photo = rs.getBlob("PHOTO1");
                File img1 = convertToImage(photo, photoSavePlace + "/" + id + "1.jpg");
                
                photo = rs.getBlob("PHOTO2");
                File img2 = convertToImage(photo, photoSavePlace + "/" + id + "2.jpg");
                
                photo = rs.getBlob("PHOTO3");
                File img3 = convertToImage(photo, photoSavePlace + "/" + id + "3.jpg");
                String p1 = rs.getString("photoPath1");
                String p2 = rs.getString("photoPath2");
                String p3 = rs.getString("photoPath3");
                
				Boolean convertShowcaseBool = false;
                if(isShowCase.equalsIgnoreCase("y"))
                {
                    convertShowcaseBool = true;
                }
                else
                {
                    convertShowcaseBool = false;
                }
				
                University univ = new University(id, name, address, city, state, phone, convertShowcaseBool, desc, url, img1, img2, img3, p1, p2, p3);
                arrUniv.add(univ);
                
                
            }

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return arrUniv;
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
                    Logger.getLogger(ShowcaseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (SQLException se) {
                    Logger.getLogger(ShowcaseDAOImpl.class.getName()).log(Level.SEVERE, null, se);
         }
        catch (IOException ioe) {
                    Logger.getLogger(ShowcaseDAOImpl.class.getName()).log(Level.SEVERE, null, ioe);
         }
        return img;
    }
        
    
}
