/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.MajorDAO;
import dao.MajorDAOImpl;
import dao.StudentInfoDAO;
import dao.StudentInfoDAOImpl;
import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import model.Major;
import model.StudentInfo;
import model.University;
import model.User;
import upload.FileUploadHandler;

/**
 *
 * @author skora
 */
@Named(value = "studentInfoController")
@SessionScoped
public class StudentInfoController implements Serializable {
        private static StudentInfo info;

    /**
     * @return the info
     */
    public static StudentInfo getInfo() {
        return info;
    }

    /**
     * @param aInfo the info to set
     */
    public void setInfo(StudentInfo aInfo) {
        info = aInfo;
    }
    /**
     * Creates a new instance of LoginController
     */
    public StudentInfoController()
    {
        info = new StudentInfo();
    }
    
    public static int createStudentInfoFromUser(User user)
    {
        String email = user.getEmailAddress();
        String fName = user.getFirstName();
        String lName = user.getLastName();
        
        StudentInfoDAO dao = new StudentInfoDAOImpl();
        
        int createStatus = dao.createStudentInfo(email, fName, lName);
        
        return createStatus;
    }
    
    public static int getStudentInfo(String email)
    {
        StudentInfoDAO dao = new StudentInfoDAOImpl();
        
        info = dao.getStudentInfo(email);
                
        UniversityDAO uDAO = new UniversityDAOImpl();
        ArrayList<University> univChoices = uDAO.getUniversitiesOfChoice(email);
        //info.setListOfUnivsInInterest(univChoices);
                
        //MajorDAO mDAO = new MajorDAOImpl();
        //ArrayList<Major> majorChoices = mDAO.getMajorsOfChoice(email);
        //info.setListOfMajorsInInterest(majorChoices);
                
        return 1;
    }
    
    public static int updateStudentInfo(StudentInfo updatedStudent)
    {
        StudentInfoDAO dao = new StudentInfoDAOImpl();
        int returnCode = dao.updateStudentInfo(updatedStudent);
        
        return returnCode;
    }
}
