/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.SignUpDAO;
import dao.SignUpDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.User;

/**
 *
 * @author skora
 */
@SessionScoped
@ManagedBean(name="signUpController")
public class SignUpController {
 //   private String response;
    
    
    public static int signUp(User newUser){             
        SignUpDAO user = new SignUpDAOImpl();
        
        int result = user.createUser(newUser);  
        return result; 
    }
    


//    /**
//     * @return the response
//     */
//    public String getResponse() {
//        String resultStr = "";
//        
//        resultStr += "You have successfuly signed up.  The following is your account information:<br />";
//        resultStr += "Name: " + user.getFirstName() + " " + user.getLastName() + "<br />";
//        resultStr += "User ID: " + user.getUserId() + "<br />";
//        resultStr += "Email: " + user.getEmail() + "<br />";
//        resultStr += "Security Question: " + user.getQuestion() + "<br />";
//        resultStr += "Security Answer: " + user.getAnswer()  + "<br />";
//        
//        response = resultStr;
//        return response;
//    }
//
//    /**
//     * @param response the response to set
//     */
//    public void setResponse(String response) {
//        this.response = response;
//    }
    
}

