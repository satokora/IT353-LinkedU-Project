/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.StudentInfo;

/**
 *
 * @author gmherr2
 */
public interface ProfileDAO {
    public StudentInfo getProfileInfo(String emailAdr, String photoPath);
}
