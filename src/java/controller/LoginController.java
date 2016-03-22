/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.LoginDAO;
import dao.LoginDAOImpl;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Named;
import model.User;

/**
 *
 * @author skora
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

        private static User user;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        user = new User();
    }
    
    public static int login(String email, String pass){

            LoginDAO dao = new LoginDAOImpl();
        
            if (dao.authenticate(email, pass)){
                user = dao.getUser(email);
                return 1;
            }
            else
            {
               
                return 0;
            }

    }



 
    /**
     * @return the userBean
     */
    public static User getUser() {
        return user;
    }

    /**
     * @param userBean the userBean to set
     */
    public void setUserBean(User user) {
        this.user = user;
    }
}
