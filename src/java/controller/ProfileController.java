/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.StudentInfo;
import model.University;

/**
 *
 * @author gmherr2
 */

@Named(value = "profileInfoController")
@SessionScoped
public class ProfileController  implements Serializable {
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
    public ProfileController()
    {
        info = new StudentInfo();
    }
    
    public static int getStudentInfo(String email, String photoPath)
    {
        ProfileDAO dao = new ProfileDAOImpl();
        info = dao.getProfileInfo(email, photoPath);
        
        UniversityDAO uDAO = new UniversityDAOImpl();
        ArrayList<University> univChoices = uDAO.getUniversitiesOfChoice(email);
        if(univChoices != null)
        {
            //info.setListOfUnivsInInterest(univChoices);
        }
        
        //MajorDAO mDAO = new MajorDAOImpl();
        //ArrayList<Major> majorChoices = mDAO.getMajorsOfChoice(email);
        //info.setListOfMajorsInInterest(majorChoices);
        
        
        System.out.println("here");
        System.out.println(info.getFirstName());
        System.out.println(info.getLastName());
        
        return 1;
    }

}
