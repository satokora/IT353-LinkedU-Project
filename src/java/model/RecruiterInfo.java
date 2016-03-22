/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;

/**
 *
 * @author skora
 * This is a StudentInfo object class which holds data retrieved from StudentInfo table in DB.
 */
public class RecruiterInfo {
    private String email;
    private University univ;
    private String firstName;
    private String lastName;
    private String phone;
    private File photoFile;
    private String description;
    public RecruiterInfo(){
        
    }
    public RecruiterInfo (String email, String firstName, String lastName,  String phone,  File photoFile, String description){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.photoFile = photoFile;
        this.description =description;
        this.univ = null;
    }
    public RecruiterInfo (String email, String firstName, String lastName,  File photoFile, String univId, String univName, String univCity, String univState, String univPhone, String univUrl, File univPhoto){
        // this constructor is used for university search
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = "";
        this.photoFile = photoFile;
        this.univ = new University(univId, univName, "", univCity, univState, univPhone, false, "", univUrl, univPhoto, null, null);
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.email = Email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }



    


    /**
     * @return the univ
     */
    public University getUniv() {
        return univ;
    }

    /**
     * @param univ the univ to set
     */
    public void setUniv(University univ) {
        this.univ = univ;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the photoFile
     */
    public File getPhotoFile() {
        return photoFile;
    }

    /**
     * @param photoFile the photoFile to set
     */
    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }
    
}
