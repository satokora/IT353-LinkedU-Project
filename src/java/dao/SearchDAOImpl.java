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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Major;
import model.RecruiterInfo;
import model.StudentInfo;


/**
 *
 * @author skora
 */
public class SearchDAOImpl {
    private String[] ColumnNamesStudentViewWithMajors = 
    new String[]{ "email", "firstname", "lastname", "address", "city", "state", "phone", "highschool", "grade", "major", "major_id", "major_name"};
    private String[] ColumnNamesStudentViewWithUnivs = 
    new String[]{ "email", "firstname", "lastname", "address", "city", "state", "phone", "highschool", "grade", "major", "univ_id", "univ_name"};
    private String[] ColumnNamesStudentInfo = 
    new String[]{ "email", "firstname", "lastname", "address", "city", "state", "phone", "highschool", "grade"};
    private String[] ColumnNamesUnivViewWithMajors = 
    new String[]{ "univ_id", "univ_name", "city", "state", "phone", "url", "description", "major_id", "major_name", "firstname", "lastname", "email"};
    private String[] ColumnNamesUnivViewWithoutMajors = 
    new String[]{ "univ_id", "univ_name", "city", "state", "phone", "url", "description", "firstname", "lastname", "email"};
    public static ArrayList<Major> getListOfMajors(){
        ArrayList<Major> listOfMajors = new ArrayList();
        DBHelper.loadDriver();
        String majorId, majorName;
        Major major;
        try {
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select * from LINKEDU.MAJOR";
                    
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next()) {
                majorId = rs.getString("MAJOR_ID");
                majorName = rs.getString("MAJOR_NAME");
                
                major = new Major(majorId, majorName);
                listOfMajors.add(major);

            }
            rs.close();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listOfMajors;
    }
    public ArrayList<StudentInfo> searchInViewWithMajors(HashMap keyset, String photoSavePlace){
        ArrayList<StudentInfo> results= new ArrayList<StudentInfo>();
        DBHelper.loadDriver();
        try{
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select email, firstname, lastname, highschool, city, state, phone, photo_file from LINKEDU.VIEW_STUSEARCH_WITH_MAJORININTEREST where";
            int counter = 0;
            for (int i = 0; i < ColumnNamesStudentViewWithMajors.length ; i++){
                
                switch (ColumnNamesStudentViewWithMajors[i]){
                    case "email":
                    case "firstname":
                    case "lastname":
                    case "highschool":
                    case "city":
                        
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " like '%" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "%'";
                            counter ++;
                        }
                        
                        break; 
                    case "state":
                    case "major_id":
                    case "phone":
                        
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " ='" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "'";
                            counter ++;
                        }
                        
                        break;
                    
                    default:
                        break;     
                }
                        
            }
            
           
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            
            String email, firstName, lastName, city, state, phone, highschool;
            Blob photo_file;
            StudentInfo stu;
            while (rs.next()) {
                email = rs.getString("EMAIL");
                firstName = rs.getString("FIRSTNAME");
                lastName = rs.getString("LASTNAME");
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                phone = rs.getString("PHONE");
                highschool = rs.getString("HIGHSCHOOL");
                photo_file = rs.getBlob("PHOTO_FILE");
                File videoFile = null;
                
                
                stu = new StudentInfo(email,firstName, lastName, "", city, state, phone, highschool, convertToImage(photo_file, photoSavePlace + "/" +firstName + lastName + ".png"), videoFile, "");
                results.add(stu);
            }
            rs.close();

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }
    public ArrayList<StudentInfo> searchInViewWithUnivs(HashMap keyset, String photoSavePlace){
        ArrayList<StudentInfo> results= new ArrayList<StudentInfo>();
        DBHelper.loadDriver();
        try{
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select email, firstname, lastname, highschool, city, state, phone, photo_file from LINKEDU.VIEW_STUSEARCH_WITH_UNIVININTEREST where";
            int counter = 0;
            for (int i = 0; i < ColumnNamesStudentViewWithMajors.length ; i++){
                switch (ColumnNamesStudentViewWithMajors[i]){
                    case "email":
                    case "firstname":
                    case "lastname":
                    case "highschool":
                    case "univ_name":
                    case "city":
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " like '%" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "%'";
                            counter ++;
                        }
                        break; 
                    case "state":
                    case "phone":
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " ='" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "'";
                            counter ++;
                        }
                        break;
                    
                    default:
                        break;     
                }
                        
            }
            
           
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            
            String email, firstName, lastName, city, state, phone, highschool;
            Blob photo_file;
            StudentInfo stu;
            while (rs.next()) {
                email = rs.getString("EMAIL");
                firstName = rs.getString("FIRSTNAME");
                lastName = rs.getString("LASTNAME");
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                phone = rs.getString("PHONE");
                highschool = rs.getString("HIGHSCHOOL");
                photo_file = rs.getBlob("PHOTO_FILE");
                File videoFile = null;
                
                
                stu = new StudentInfo(email,firstName, lastName, "", city, state, phone, highschool, convertToImage(photo_file, photoSavePlace + "/" +firstName + lastName + ".png"), videoFile, "");
                results.add(stu);
            }
            rs.close();

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }
    public ArrayList<StudentInfo> searchInStudentInfo(HashMap keyset, String photoSavePlace){
        ArrayList<StudentInfo> results= new ArrayList<StudentInfo>();
        DBHelper.loadDriver();
        try{
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select email, firstname, lastname, highschool, city, state, phone, photo_file from LINKEDU.STUDENTINFO where";
            int counter = 0;
            for (int i = 0; i < ColumnNamesStudentViewWithMajors.length ; i++){
                switch (ColumnNamesStudentViewWithMajors[i]){
                    case "email":
                    case "firstname":
                    case "lastname":
                    case "highschool":
                    case "city":
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " like '%" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "%'";
                            counter ++;
                        }
                        break; 
                    case "state":
                    case "phone":
                        if (keyset.containsKey(ColumnNamesStudentViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesStudentViewWithMajors[i] + " ='" + keyset.get(ColumnNamesStudentViewWithMajors[i]) + "'";
                            counter ++;
                        }
                        break;
                    
                    default:
                        break;     
                }
                        
            }
            
           
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            
            String email, firstName, lastName, city, state, phone, highschool;
            Blob photo_file;
            StudentInfo stu;
            while (rs.next()) {
                email = rs.getString("EMAIL");
                firstName = rs.getString("FIRSTNAME");
                lastName = rs.getString("LASTNAME");
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                phone = rs.getString("PHONE");
                highschool = rs.getString("HIGHSCHOOL");
                photo_file = rs.getBlob("PHOTO_FILE");
                File videoFile = null;
                
                
                stu = new StudentInfo(email,firstName, lastName, "", city, state, phone, highschool, convertToImage(photo_file, photoSavePlace + "/" +firstName + lastName + ".png"), videoFile, "");
                results.add(stu);
            }
            rs.close();

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }
    public ArrayList<RecruiterInfo> searchSchoolsWithMajors(HashMap keyset, String photoSavePlace){
        ArrayList<RecruiterInfo> results= new ArrayList<RecruiterInfo>();
        DBHelper.loadDriver();
        try{
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select univ_id, univ_name, city, state, phone, url, photo1, description, major_id, major_name, firstname, lastname, email, photo_file from LINKEDU.VIEW_UNIVSEARCH_WITH_MAJORS where";
            int counter = 0;
            for (int i = 0; i < ColumnNamesUnivViewWithMajors.length ; i++){
                switch (ColumnNamesUnivViewWithMajors[i]){
                    case "univ_name":
                    case "city":
                    case "description":
                        if (keyset.containsKey(ColumnNamesUnivViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesUnivViewWithMajors[i] + " like '%" + keyset.get(ColumnNamesUnivViewWithMajors[i]) + "%'";
                            counter ++;
                        }
                        break; 
                    case "state":
                    case "major_id":
                        if (keyset.containsKey(ColumnNamesUnivViewWithMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesUnivViewWithMajors[i] + " ='" + keyset.get(ColumnNamesUnivViewWithMajors[i]) + "'";
                            counter ++;
                        }
                        break;
                    case "grade": // need to add between statement
                    default:
                        break;     
                }
                        
            }
            
           
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            
            String univId, univName, city, state, phone, url, description, firstName, lastName, email;
            Blob photo1, photo_file;
            RecruiterInfo rec;
            while (rs.next()) {
                univId = rs.getString("UNIV_ID");
                univName = rs.getString("UNIV_NAME");
                
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                phone = rs.getString("PHONE");
                url = rs.getString("URL");
                photo1 = rs.getBlob("PHOTO1");
                description = rs.getString("DESCRIPTION");
                firstName = rs.getString("FIRSTNAME");
                lastName = rs.getString("LASTNAME");
                email = rs.getString("EMAIL");
                photo_file = rs.getBlob("PHOTO_FILE");
                
                File univPhoto = null;
                File recruiterPhoto = null;
                
                univPhoto = convertToImage(photo1, photoSavePlace + "/" + univId + "1.jpg");
                recruiterPhoto = convertToImage(photo_file, photoSavePlace + "/" +firstName + lastName + ".png");
                
                rec = new RecruiterInfo (email, firstName, lastName, recruiterPhoto , univId, univName, city, state, phone, url, univPhoto);
                
                results.add(rec);
            }
            rs.close();

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }
    public ArrayList<RecruiterInfo> searchSchoolsWithoutMajors(HashMap keyset, String photoSavePlace){
        ArrayList<RecruiterInfo> results= new ArrayList<RecruiterInfo>();
        DBHelper.loadDriver();
        try{
            Connection DBConn = DBHelper.connect2DB();
            String selectString;
            Statement stmt = DBConn.createStatement();
            selectString = "select univ_id, univ_name, city, state, phone, url, photo1, description, firstname, lastname, email, photo_file from LINKEDU.VIEW_UNIVSEARCH_WITHOUT_MAJORS where";
            int counter = 0;
            for (int i = 0; i < ColumnNamesUnivViewWithoutMajors.length ; i++){
                switch (ColumnNamesUnivViewWithoutMajors[i]){
                    case "univ_name":
                    case "city":
                    case "description":
                        if (keyset.containsKey(ColumnNamesUnivViewWithoutMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesUnivViewWithoutMajors[i] + " like '%" + keyset.get(ColumnNamesUnivViewWithoutMajors[i]) + "%'";
                            counter ++;
                        }
                        break; 
                    case "state":
                        if (keyset.containsKey(ColumnNamesUnivViewWithoutMajors[i])){
                            if (counter > 0){
                                selectString += " and";
                            }
                            selectString += " " + ColumnNamesUnivViewWithoutMajors[i] + " ='" + keyset.get(ColumnNamesUnivViewWithoutMajors[i]) + "'";
                            counter ++;
                        }
                        break;
                    case "grade": // need to add between statement
                    default:
                        break;     
                }
                        
            }
            
           
            
            System.out.println("select string =" + selectString);
            ResultSet rs = stmt.executeQuery(selectString);
            
            String univId, univName, city, state, phone, url, description, firstName, lastName, email;
            Blob photo1, photo_file;
            RecruiterInfo rec;
            while (rs.next()) {
                univId = rs.getString("UNIV_ID");
                univName = rs.getString("UNIV_NAME");
                
                city = rs.getString("CITY");
                state = rs.getString("STATE");
                phone = rs.getString("PHONE");
                url = rs.getString("URL");
                photo1 = rs.getBlob("PHOTO1");
                description = rs.getString("DESCRIPTION");
                firstName = rs.getString("FIRSTNAME");
                lastName = rs.getString("LASTNAME");
                email = rs.getString("EMAIL");
                photo_file = rs.getBlob("PHOTO_FILE");
                
                File univPhoto = null;
                File recruiterPhoto = null;
                
                univPhoto = convertToImage(photo1, photoSavePlace + "/" + univId + "1.jpg");
                recruiterPhoto = convertToImage(photo_file, photoSavePlace + "/" +firstName + lastName + ".png");
                
                rec = new RecruiterInfo (email, firstName, lastName, recruiterPhoto , univId, univName, city, state, phone, url, univPhoto);
                
                results.add(rec);
            }
            rs.close();

            DBConn.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
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
