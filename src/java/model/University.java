/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author skora
 */
public class University {
    private String universityId;
    private String universityName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private Boolean IsShowCase;
    private String description;
    private String url;
    private File photo1;
    private File photo2;
    private File photo3;
    private ArrayList<Major> listOfMajors;
    private String photoPath1;
    private String photoPath2;
    private String photoPath3;

    public University(){
        
    }
    public University(String universityId, String universityName, String address, String city, String state, String phone, Boolean isShowCase, String description, String url, File photo1, File photo2, File photo3){
        this.universityId = universityId;
        this.universityName = universityName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.IsShowCase = isShowCase;
        this.description = description;
        this.url = url;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;

    }
    
    public University(String universityId, String universityName, String address, String city, String state, String phone, Boolean isShowCase, String description, String url, File photo1, File photo2, File photo3, String photoPath1, String photoPath2, String photoPath3){
        this.universityId = universityId;
        this.universityName = universityName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.IsShowCase = isShowCase;
        this.description = description;
        this.url = url;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        
        this.photoPath1 = photoPath1;
        this.photoPath2 = photoPath2;
        this.photoPath3 = photoPath3;

    }
    
    

    /**
     * @return the universityId
     */
    public String getUniversityId() {
        return universityId;
    }

    /**
     * @param universityId the universityId to set
     */
    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
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
     * @return the IsShowCase
     */
    public Boolean getIsShowCase() {
        return IsShowCase;
    }

    /**
     * @param IsShowCase the IsShowCase to set
     */
    public void setIsShowCase(Boolean IsShowCase) {
        this.IsShowCase = IsShowCase;
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
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

 

    /**
     * @return the listOfMajors
     */
    public ArrayList<Major> getListOfMajors() {
        return listOfMajors;
    }

    /**
     * @param listOfMajors the listOfMajors to set
     */
    public void setListOfMajors(ArrayList<Major> listOfMajors) {
        this.listOfMajors = listOfMajors;
    }

    /**
     * @return the photo1
     */
    public File getPhoto1() {
        return photo1;
    }

    /**
     * @param photo1 the photo1 to set
     */
    public void setPhoto1(File photo1) {
        this.photo1 = photo1;
    }

    /**
     * @return the photo2
     */
    public File getPhoto2() {
        return photo2;
    }

    /**
     * @param photo2 the photo2 to set
     */
    public void setPhoto2(File photo2) {
        this.photo2 = photo2;
    }

    /**
     * @return the photo3
     */
    public File getPhoto3() {
        return photo3;
    }

    /**
     * @param photo3 the photo3 to set
     */
    public void setPhoto3(File photo3) {
        this.photo3 = photo3;
    }

    /**
     * @return the photoPath1
     */
    public String getPhotoPath1() {
        return photoPath1;
    }

    /**
     * @param photoPath1 the photoPath1 to set
     */
    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    /**
     * @return the photoPath2
     */
    public String getPhotoPath2() {
        return photoPath2;
    }

    /**
     * @param photoPath2 the photoPath2 to set
     */
    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    /**
     * @return the photoPath3
     */
    public String getPhotoPath3() {
        return photoPath3;
    }

    /**
     * @param photoPath3 the photoPath3 to set
     */
    public void setPhotoPath3(String photoPath3) {
        this.photoPath3 = photoPath3;
    }

    

} 