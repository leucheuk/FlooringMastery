/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.service;

import com.sherlock.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sherlock.flooringmastery.dto.Order;
import com.sherlock.flooringmastery.dto.Product;
import com.sherlock.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public interface FlooringMasteryServiceLayer {
    void createOrder(Order order) throws
            FlooringMasteryDuplicateIdException,
            FlooringMasteryDataValidationException,
            FlooringMasteryPersistenceException;
 
        void replaceOrder(Order order) throws
            FlooringMasteryDataValidationException,
            FlooringMasteryPersistenceException;
 
    
    
    List<Order> getAllOrders(String orderDate) throws
            FlooringMasteryPersistenceException;
 
    Order getOrder(String orderNumber) throws
            FlooringMasteryPersistenceException;
    

    Order removeOrder(String studentId) throws
            FlooringMasteryPersistenceException;
    
    
    Order updateOrderTotal (Order order) throws 
            FlooringMasteryPersistenceException,
            FlooringMasteryDataValidationException;
    
    void exportAllOrders()  throws
         FlooringMasteryPersistenceException;
    
    void createTax(Tax tax) throws
            FlooringMasteryDuplicateIdException,
            FlooringMasteryDataValidationException,
            FlooringMasteryPersistenceException;
 
    List<Tax> getAllTaxes() throws
            FlooringMasteryPersistenceException;
 
    Tax getTax(String state) throws
            FlooringMasteryPersistenceException;
 
    Tax removeTax(String state) throws
            FlooringMasteryPersistenceException;
    
    
    
    void createProduct(Product product) throws
            FlooringMasteryDuplicateIdException,
            FlooringMasteryDataValidationException,
            FlooringMasteryPersistenceException;
 
    List<Product> getAllProducts() throws
            FlooringMasteryPersistenceException;
 
    Product getProduct(String productType) throws
            FlooringMasteryPersistenceException;
 
    Product removeProduct(String productType) throws
            FlooringMasteryPersistenceException;

}
