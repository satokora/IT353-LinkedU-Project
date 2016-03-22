/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import model.University;

/**
 *
 * @author skora
 */
public interface UniversityDAO
{
    public boolean authenticate(String univID, String univName);
    public University getUniversity(String univID);
    public ArrayList<University> getUniversitiesOfChoice(String email);
}
