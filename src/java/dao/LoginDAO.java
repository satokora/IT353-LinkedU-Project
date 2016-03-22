/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.User;

/**
 *
 * @author skora
 */
public interface LoginDAO {
    public boolean authenticate(String userid, String pass);
    public User getUser(String userid);
}
