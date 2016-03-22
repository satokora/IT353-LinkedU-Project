/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.SearchDAOImpl;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.RecruiterInfo;
import model.StudentInfo;
import model.University;

/**
 *
 * @author skora
 */
@SessionScoped
@ManagedBean(name="searchStudentsController")
public class SearchController {

    public ArrayList<StudentInfo> searchForStudents(HashMap criteria, String photoFilePath){
        ArrayList<StudentInfo> results = null;
        SearchDAOImpl dao = new SearchDAOImpl();
        
        if (criteria.containsKey("major_id") && criteria.containsKey("univ_name")){
            // retrieve data from VIEW_STUSEARCH_WITH_MAJORININTERST and VIEW_STUSEARCH_WITH_UNIVININTERST
            // and compare both sets of data
            ArrayList<StudentInfo> tempResults1 = dao.searchInStudentInfo(criteria, photoFilePath);
            ArrayList<StudentInfo> tempResults2 = dao.searchInViewWithUnivs(criteria, photoFilePath);
            //Store the email address list of tempResults in order to remove redundant student information between tempResults1 and tempResults2
            ArrayList<String> arrOfEmails = new ArrayList<String>();
            
            for (int x = 0; x < tempResults1.size(); x++){
                results.add(tempResults1.get(x));
                arrOfEmails.add(tempResults1.get(x).getEmail());
            }
            
            for (int y = 0; y < tempResults2.size(); y++){
                if (!arrOfEmails.contains(tempResults2.get(y).getEmail())){
                    results.add(tempResults2.get(y));
                }
                
            }
            
        }else if (!criteria.containsKey("major_id") && !criteria.containsKey("univ_name")){
            //retireve data only from student info
            results = dao.searchInStudentInfo(criteria, photoFilePath);
            
        }else if (criteria.containsKey("major_id")){
            //retireve data only from VIEW_STUSEARCH_WITH_MAJORININTERST
            results = dao.searchInViewWithMajors(criteria, photoFilePath);
            
        }else if (criteria.containsKey("univ_name")){
            // retrieve data only from VIEW_STUSEARCH_WITH_UNIVININTERST
            results = dao.searchInViewWithUnivs(criteria, photoFilePath);
        }
        
        return results;
    }
    public ArrayList<RecruiterInfo> searchForSchools(HashMap criteria, String photoFilePath){
        ArrayList<RecruiterInfo> results = new ArrayList<RecruiterInfo>();
        SearchDAOImpl dao = new SearchDAOImpl();
        
        if (criteria.containsKey("major_id")){
            //retrieve data only from VIEW_STUSEARCH_WITH_MAJORININTERST
            results = dao.searchSchoolsWithMajors(criteria, photoFilePath);
            
        }else{
            // retrieve data only from VIEW_STUSEARCH_WITH_UNIVININTERST
            results = dao.searchSchoolsWithoutMajors(criteria, photoFilePath);
        }
        
        return results;
    }
    
}
