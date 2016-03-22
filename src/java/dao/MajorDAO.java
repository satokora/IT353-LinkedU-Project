/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import model.Major;

/**
 *
 * @author skora
 */
public interface MajorDAO
{
    public boolean authenticate(String majID, String majName);
    public Major getMajor(String majID, String majName);
    public ArrayList<Major> getMajorsOfChoice(String email);
}
