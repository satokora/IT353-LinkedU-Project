/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author skora
 * This is a StudentInfo object class which holds data retrieved from StudentInfo table in DB.
 */
public class StudentInfo {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String highSchool;
    private File photoFileObject;
    private File videoFileObject;
    private String biography;
    private ArrayList<University> listOfUnivsInInterest;
    private ArrayList<Major> listOfMajorsInInterest;
	
	//merge 12/4/14
    private File photoFilePath;
    private Blob photoFile;
    private Blob videoFile;
	private String grade;
    private String major;
	
    
    public StudentInfo(){
        
    }
	//merge 12/4/14
	//Populates all entries including the Blob files
    public StudentInfo (String email, String firstName, String lastName, String address, String city, String state, String phone, String highSchool, Blob photoFilePath, Blob videoFilePath, String biography,String gde,String maj){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state =state;
        this.phone = phone;
        this.highSchool = highSchool;
        this.photoFile = photoFilePath;
        this.videoFile = videoFilePath;
        this.biography = biography;
        grade = gde;
        major = maj;
    }
	
    //Populates entries BESIDES Blob files
    public StudentInfo (String email, String firstName, String lastName, String address, String city, String state, String phone, String highSchool, String biography,String gde,String maj){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state =state;
        this.phone = phone;
        this.highSchool = highSchool;
        this.biography = biography;
        grade = gde;
        major = maj;
    }
    
    public StudentInfo (String email, String firstName, String lastName, String address, String city, String state, String phone, String highSchool, File photoFilePath, Blob videoFilePath, String biography){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state =state;
        this.phone = phone;
        this.highSchool = highSchool;
        this.photoFilePath = photoFilePath;
        this.videoFile = videoFilePath;
        this.biography = biography;
    }
	
    public StudentInfo (String email, String firstName, String lastName, String address, String city, String state, String phone, String highSchool, File photoFileObject, File videoFileObject, String biography){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state =state;
        this.phone = phone;
        this.highSchool = highSchool;
        this.photoFileObject = photoFileObject;
        this.videoFileObject = videoFileObject;
        this.biography = biography;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
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
     * @return the highSchool
     */
    public String getHighSchool() {
        return highSchool;
    }

    /**
     * @param highSchool the highSchool to set
     */
    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

 

    /**
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * @return the listOfUnivsInInterest
     */
    public ArrayList<University> getListOfUnivsInInterest() {
        return listOfUnivsInInterest;
    }

    /**
     * @param listOfUnivsInInterest the listOfUnivsInInterest to set
     */
    public void setListOfUnivsInInterest(ArrayList<University> listOfUnivsInInterest) {
        this.listOfUnivsInInterest = listOfUnivsInInterest;
    }

    /**
     * @return the listOfMajorsInInterest
     */
    public ArrayList<Major> getListOfMajorsInInterest() {
        return listOfMajorsInInterest;
    }

    /**
     * @param listOfMajorsInInterest the listOfMajorsInInterest to set
     */
    public void setListOfMajorsInInterest(ArrayList<Major> listOfMajorsInInterest) {
        this.listOfMajorsInInterest = listOfMajorsInInterest;
    }

    /**
     * @return the photoFileObject
     */
    public File getPhotoFileObject() {
        return photoFileObject;
    }

    /**
     * @param photoFileObject the photoFile to set
     */
    public void setPhotoFileObject(File photoFileObject) {
        this.photoFileObject = photoFileObject;
    }

    /**
     * @return the videoFile
     */
    public File getVideoFileObject() {
        return videoFileObject;
    }

    /**
     * @param videoFile the videoFile to set
     */
    public void setVideoFileObject(File videoFileObject) {
        this.videoFileObject = videoFileObject;
    }
	
	/**
     * @return the photoFile
     */
    public Blob getPhotoFile() {
        return photoFile;
    }

    /**
     * @param photoFile the photoFile to set
     */
    public void setPhotoFile(Blob photoFile) {
        this.photoFile = photoFile;
    }

    /**
     * @return the videoFile
     */
    public Blob getVideoFile() {
        return videoFile;
    }

    /**
     * @param videoFile the videoFile to set
     */
    public void setVideoFile(Blob videoFile) {
        this.videoFile = videoFile;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getUnivsInInterest() {
        String universitys = "";
        if(listOfUnivsInInterest != null){
            for(int i = 0; i < listOfUnivsInInterest.size(); i++){
                if(this.listOfUnivsInInterest.get(i).getUniversityName()!= null)
                {
                    //universitys += " - " + this.listOfUnivsInInterest.get(i).getUniversityName() + "\n";
                }
               
            }
        }
        
        return universitys;
    }
    
    public String getMajorsInInterest() {
        String majors = "";
        if(listOfMajorsInInterest != null){
            for(int i = 0; i < listOfMajorsInInterest.size(); i++){
                majors += "- " + this.listOfMajorsInInterest.get(i).getMajorName() + "\n";
            }
        }
        
        return majors;
    }
    
}
