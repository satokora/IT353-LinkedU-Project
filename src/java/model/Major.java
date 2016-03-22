/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author skora
 */
public class Major {
    private String majorId;
    private String majorName;
    
    public Major(){
        
    }
    public Major(String majorId, String majorName){
        this.majorId = majorId;
        this.majorName = majorName;
    }
    /**
     * @return the majorId
     */
    public String getMajorId() {
        return majorId;
    }

    /**
     * @param majorId the majorId to set
     */
    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    /**
     * @return the majorName
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * @param majorName the majorName to set
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
}
