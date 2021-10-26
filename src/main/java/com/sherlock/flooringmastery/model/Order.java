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
public class Order {
    private String orderNumber;
    private String orderDate;
    private String customerName;
    private String state;
    private String taxRate;
    private String productType;
    private String area;
    private String costPerSquareFoot;
    private String laborCostPerSquareFoot;
    private String materialCost;
    private String laborCost;
    private String tax;
    private String total;
    
    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    } 
    
    public String getorderNumber() {
     return orderNumber;
    }
    
    public String getcustomerName() {
    return customerName;
    }
    
    public String getstate() {
    return state;
    }

    public String gettaxRate() {
    return taxRate;
    }
    
    public String getproductType() {
    return productType;
    }

    public String getarea() {
    return area;
    }    
    
    public String getcostPerSquareFoot() {
    return costPerSquareFoot;
    }    
    public String getlaborCostPerSquareFoot() {
    return laborCostPerSquareFoot;
    }

    public String getmaterialCost() {
    return materialCost;
    } 
    public String getlaborCost() {
    return laborCost;
    }
    public String gettax() {
    return tax;
    }
    public String gettotal() {
    return total;
    }
    
    public String getOrderDate() {
    return orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }   

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }       
    
    public void setstate(String state) {
        this.state = state;
    }  
    
    public void settaxRate(String taxRate) {
        this.taxRate = taxRate;
    }  
    
    public void setproductType(String productType) {
        this.productType = productType;
    }     
    
    public void setarea(String area) {
        this.area = area;
    } 
    public void setcostPerSquareFoot(String costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    } 
    public void setlaborCostPerSquareFoot(String laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    public void setmaterialCost(String materialCost) {
        this.materialCost = materialCost;
    }
    public void setlaborCost(String laborCost) {
        this.laborCost = laborCost;
    }    
    
    public void settax(String tax) {
        this.tax = tax;
    }  

    public void settotal(String total) {
        this.total = total;
    }      
    
}
