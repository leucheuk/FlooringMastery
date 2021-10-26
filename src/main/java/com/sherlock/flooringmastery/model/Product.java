/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.model;

/**
 *
 * @author Sherlock
 */
public class Product {
    private String productType;
    private String costPerSquareFoot;
    private String laborCostPerSquareFoot;
    
    public Product(String productType) {
     this.productType = productType;
    } 
    
    public String getproductType() {
      return productType;
    }
        
    public String getcostPerSquareFoot() {
      return costPerSquareFoot;
    }
            
    public String getlaborCostPerSquareFoot() {
     return laborCostPerSquareFoot;
    }
    
    public void setproductType(String productType) {
       this.productType = productType;
    }  
    
    public void setcostPerSquareFoot(String costPerSquareFoot) {
      this.costPerSquareFoot = costPerSquareFoot;
    }  
        
    public void setlaborCostPerSquareFoot(String laborCostPerSquareFoot) {
      this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }  
                
                
}
