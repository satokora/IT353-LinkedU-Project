/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.StudentInfo;

/**
 *
 * @author skora
 */
public interface StudentInfoDAO {
    public boolean authenticate(String email);
    public int createStudentInfo(String email, String firstName, String lastName);
    public StudentInfo getStudentInfo(String emailAdr);
    public int updateStudentInfo(StudentInfo studentInfo);
}
