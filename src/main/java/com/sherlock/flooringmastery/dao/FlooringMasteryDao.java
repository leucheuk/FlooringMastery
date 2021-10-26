/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dao;

import com.sherlock.flooringmastery.model.Order;
import com.sherlock.flooringmastery.model.Product;
import com.sherlock.flooringmastery.model.Tax;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public interface FlooringMasteryDao {
   
    Order addOrder(String orderNumber, Order order)
            throws FlooringMasteryPersistenceException;
    
    List<Order> getAllOrders()
            throws FlooringMasteryPersistenceException;
    
    Order getOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    Order removeOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    void exportAlldata() 
          throws FlooringMasteryPersistenceException;
    
    
    Tax addTax(String state, Tax tax)
            throws FlooringMasteryPersistenceException;
    
    List<Tax> getAllTaxes()
            throws FlooringMasteryPersistenceException;
    
    Tax getTax(String state)
            throws FlooringMasteryPersistenceException;
    
    Tax removeTax(String state)
            throws FlooringMasteryPersistenceException;
    
    
    Product addProduct(String productType, Product product)
            throws FlooringMasteryPersistenceException;
    
    List<Product> getAllProducts()
            throws FlooringMasteryPersistenceException;
    
    Product getProduct(String productType)
            throws FlooringMasteryPersistenceException;
    
    Product removeProduct(String productType)
            throws FlooringMasteryPersistenceException;
    

}
