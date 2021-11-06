/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dto;

/**
 *
 * @author Sherlock
 */
public class Tax {
    private String state;
    private String stateName;
    private String taxRate;
    
    public Tax(String state) {
     this.state = state;
    }  
    
    public String getstate() {
    return state;
    }
    
    public String getstateName() {
    return stateName;
    }
    
    public String gettaxRate() {
    return taxRate;
    }


    
    public void setstate(String state) {
    this.state = state;
    }  

    public void setstateName(String stateName) {
    this.stateName = stateName;
    } 
    
    public void settaxRate(String taxRate) {
    this.taxRate = taxRate;
    }  
    
}
