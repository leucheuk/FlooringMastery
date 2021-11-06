/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.service;

import com.sherlock.flooringmastery.dao.FlooringMasteryDao;
import com.sherlock.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sherlock.flooringmastery.dto.Order;
import com.sherlock.flooringmastery.dto.Product;
import com.sherlock.flooringmastery.dto.Tax;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

        public  Order onlyOrder;
    
        public FlooringMasteryDaoStubImpl() {
        onlyOrder = new Order("0001");
        onlyOrder.setOrderDate("12122021");
        onlyOrder.setcustomerName("David Chu");
        onlyOrder.setstate("CA");
        onlyOrder.settaxRate("25.00");
        onlyOrder.setarea("249.00");
        onlyOrder.setproductType("Tile");
        onlyOrder.setcostPerSquareFoot("3.50");
        onlyOrder.setmaterialCost("871.50");
        onlyOrder.setlaborCostPerSquareFoot("4.15");
        onlyOrder.setlaborCost("1033.35");
        onlyOrder.settax("476.21");
        onlyOrder.settotal("2381.06");
        }

         public FlooringMasteryDaoStubImpl(Order testOrder){
         this.onlyOrder = testOrder;
     }
    
    
    @Override
    public Order addOrder(String orderNumber, Order order) throws FlooringMasteryPersistenceException {
           if (orderNumber.equals(onlyOrder.getorderNumber())) {
            return onlyOrder;
        } else {
            return null;
        } 
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public Order getOrder(String orderNumber) throws FlooringMasteryPersistenceException {
              if (orderNumber.equals(onlyOrder.getorderNumber())) {
            return onlyOrder;
        } else {
            return null;
       }
    }

    @Override
    public Order removeOrder(String orderNumber) throws FlooringMasteryPersistenceException {
              if (orderNumber.equals(onlyOrder.getorderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public void exportAlldata() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax addTax(String state, Tax tax) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax removeTax(String state) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(String productType, Product product) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
