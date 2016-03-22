package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author ajauer
 */
public class SignUpValidator {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String userType;
    
    public SignUpValidator(String fN, String lN, String eM, String pW, String uT)
    {
        fName = fN;
        lName = lN;
        email = eM;
        password = pW;
        userType = uT;
    }
    
    public boolean checkName()
    {
        for (int i=0; i< fName.length(); i++)
            if (Character.isDigit(fName.charAt(i)))
                return false;
        for (int i=0; i< lName.length(); i++)
            if (Character.isDigit(lName.charAt(i)))
                return false;
        return true;
    }
    
    public boolean checkEmail()
    {
        boolean isValid = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            
        }
        return isValid;
    }
    
    public boolean checkPassword()
    {
        return password.length() >= 8;
    }
    
    public boolean checkUserType()
    {
        return (userType.equals("S") || userType.equals("R"));
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
