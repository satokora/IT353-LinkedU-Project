/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author skora
 */
public class State {
    private ArrayList<String> listOfStates;
    public State(){
        listOfStates = new ArrayList();
        
        listOfStates.add("AL");
        listOfStates.add("AK");
        listOfStates.add("AZ");
        listOfStates.add("AR");
        listOfStates.add("CA");
        listOfStates.add("CO");
        listOfStates.add("CT");
        listOfStates.add("DE");
        listOfStates.add("FL");
        listOfStates.add("GA");
        listOfStates.add("HI");
        listOfStates.add("ID");
        listOfStates.add("IL");
        listOfStates.add("IN");
        listOfStates.add("IA");
        listOfStates.add("KS");
        listOfStates.add("KY");
        listOfStates.add("LA");
        listOfStates.add("ME");
        listOfStates.add("MD");
        listOfStates.add("MA");
        listOfStates.add("MI");
        listOfStates.add("MN");
        listOfStates.add("MS");
        listOfStates.add("MO");
        listOfStates.add("MT");
        listOfStates.add("NE");
        listOfStates.add("NV");
        listOfStates.add("NH");
        listOfStates.add("NJ");
        listOfStates.add("NM");
        listOfStates.add("NY");
        listOfStates.add("NC");
        listOfStates.add("ND");
        listOfStates.add("OH");
        listOfStates.add("OK");
        listOfStates.add("OR");
        listOfStates.add("PA");
        listOfStates.add("RI");
        listOfStates.add("SC");
        listOfStates.add("SD");
        listOfStates.add("TN");
        listOfStates.add("TX");
        listOfStates.add("UT");
        listOfStates.add("VT");
        listOfStates.add("VA");
        listOfStates.add("WA");
        listOfStates.add("WV");
        listOfStates.add("WI");
        listOfStates.add("WY");
    }

    /**
     * @return the listOfStates
     */
    public ArrayList<String> getListOfStates() {
        return listOfStates;
    }

    /**
     * @param listOfStates the listOfStates to set
     */
    public void setListOfStates(ArrayList<String> listOfStates) {
        this.listOfStates = listOfStates;
    }
    
}
