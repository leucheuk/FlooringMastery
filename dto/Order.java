/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dto;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", orderDate=" + orderDate +
                ", customerName=" + customerName + ", state=" + state + 
                ", taxRate=" + taxRate + ", productType=" + productType + 
                ", area=" + area + ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ", materialCost=" + materialCost + ", laborCost=" + laborCost +
                ", tax=" + tax + ", total=" + total + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.orderNumber);
        hash = 37 * hash + Objects.hashCode(this.orderDate);
        hash = 37 * hash + Objects.hashCode(this.customerName);
        hash = 37 * hash + Objects.hashCode(this.state);
        hash = 37 * hash + Objects.hashCode(this.taxRate);
        hash = 37 * hash + Objects.hashCode(this.productType);
        hash = 37 * hash + Objects.hashCode(this.area);
        hash = 37 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.materialCost);
        hash = 37 * hash + Objects.hashCode(this.laborCost);
        hash = 37 * hash + Objects.hashCode(this.tax);
        hash = 37 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
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
