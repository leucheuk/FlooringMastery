/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dao;

/**
 *
 * @author Sherlock
 */
public class FlooringMasteryPersistenceException extends Exception{
    
    public FlooringMasteryPersistenceException(String message) {
        super(message);
    }
    
    public FlooringMasteryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}