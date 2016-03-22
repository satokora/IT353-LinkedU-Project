/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.ShowcaseDAOImpl;
import java.util.ArrayList;
import java.util.Random;
import model.University;

/**
 *
 * @author skora
 */
public class ShowCaseController {
    private ArrayList<University> arrFUniv;
    public ShowCaseController(String photoFilePath){
        ShowcaseDAOImpl dao = new ShowcaseDAOImpl();
        this.arrFUniv = dao.getFeaturedUniversities(photoFilePath);
    }
    public University pickOneFeaturedUniversity(){
        University[] arrUniv = arrFUniv.toArray(new University[arrFUniv.size()]);
        Random univPicker = new Random();
        
        int ranNum = univPicker.nextInt(arrUniv.length);
        
        return arrUniv[ranNum];
    }
    public ArrayList<University> getAllFeaturedUnivs(){
        return arrFUniv;
    }


    
}
