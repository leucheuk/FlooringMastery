/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.service;

import com.sherlock.flooringmastery.dao.FlooringMasteryDao;
import com.sherlock.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sherlock.flooringmastery.model.Order;
import com.sherlock.flooringmastery.model.Product;
import com.sherlock.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;



/**
 *
 * @author Sherlock
 */
public class FlooringMasteryServiceLayerFileImpl implements
        FlooringMasteryServiceLayer {
    
        public static final BigDecimal ONE_HUNDRED = new BigDecimal(100.00);
    
        FlooringMasteryDao dao;
        
        public  FlooringMasteryServiceLayerFileImpl( FlooringMasteryDao dao) {
              this.dao = dao;
             }
    
            @Override
          public void createOrder(Order order) throws
                  FlooringMasteryDuplicateIdException,
                  FlooringMasteryDataValidationException,
                  FlooringMasteryPersistenceException {
                
              
               if (dao.getOrder(order.getorderNumber()) != null) {
                   throw new FlooringMasteryDuplicateIdException(
                    "ERROR: Could not create order.  Order#"
                    + order.getorderNumber()
                    + " already exists");
                   } 
                 validateOrderData(order);   
                 
                 updateOrderTotal(order);
                 dao.addOrder(order.getorderNumber(), order);
          }
          
          
                @Override
          public void replaceOrder(Order order) throws
                  FlooringMasteryDataValidationException,
                  FlooringMasteryPersistenceException {
                
                 validateOrderData(order);   
                 
                 updateOrderTotal(order);
                 dao.addOrder(order.getorderNumber(), order);
          }
                
          
          
          

          @Override
          public List<Order> getAllOrders( String orderDate) throws
                  FlooringMasteryPersistenceException {
          String orderDateMod = orderDate.substring(0, 2) + orderDate.substring(3, 5) + orderDate.substring(6, 10);
//               return dao.getAllOrders();
        return dao.getAllOrders().stream().filter((p) -> p.getOrderDate().compareTo(orderDateMod)==0).collect(Collectors.toList());
          }

          @Override
          public Order getOrder(String orderNumber) throws
                  FlooringMasteryPersistenceException {
               return dao.getOrder(orderNumber);
          }

          @Override
          public Order removeOrder(String orderNumber) throws
                  FlooringMasteryPersistenceException {
              return dao.removeOrder(orderNumber);
          }  

    
    private void validateOrderData(Order order) throws
        FlooringMasteryDataValidationException {

          if (order.getcustomerName()== null
            || order.getcustomerName().trim().length() == 0
            || order.getstate()== null
            || order.getstate().trim().length() == 0
            || order.getproductType()== null
            || order.getproductType().trim().length() == 0               
            || order.getarea()== null
            || order.getarea().trim().length() == 0            
                                                   ) {
                   throw new  FlooringMasteryDataValidationException(
                     "ERROR: All fields are required.");
                 }
        }      

    @Override
    public void createTax(Tax tax) throws
                  FlooringMasteryDuplicateIdException,
                  FlooringMasteryDataValidationException,
                  FlooringMasteryPersistenceException {
                 if (dao.getTax(tax.getstate()) != null) {
                   throw new FlooringMasteryDuplicateIdException(
                    "ERROR: Could not create Tax.  State:"
                    + tax.getstate()
                    + " already exists");
                   }
                 
                 validateTaxData(tax);   
                 
                 dao.addTax(tax.getstate(), tax);
          }

    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
               return dao.getAllTaxes();
    }


    @Override
    public Tax getTax(String state) 
            throws FlooringMasteryPersistenceException {
                  return dao.getTax(state);
    }

    @Override
    public Tax removeTax(String state)
            throws FlooringMasteryPersistenceException {
              return dao.removeTax(state);
    }

    private void validateTaxData(Tax tax) throws
        FlooringMasteryDataValidationException {

          if (tax.getstateName()== null
            || tax.getstateName().trim().length() == 0

            || tax.gettaxRate()== null
            || tax.gettaxRate().trim().length() == 0                      
                                                   ) {
                   throw new  FlooringMasteryDataValidationException(
                     "ERROR: All fields are required.");
                 }
        }   
      
    @Override
    public void createProduct(Product product) throws 
                  FlooringMasteryDuplicateIdException,
                  FlooringMasteryDataValidationException,
                  FlooringMasteryPersistenceException {
                 if (dao.getProduct(product.getproductType()) != null) {
                   throw new FlooringMasteryDuplicateIdException(
                    "ERROR: Could not create Product.  Product: "
                    + product.getproductType()
                    + " already exists");
                   }
                 
                 validateProductData(product);   
                 
                 dao.addProduct(product.getproductType(), product);
          }


    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
               return dao.getAllProducts();
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
                  return dao.getProduct(productType);    }

    @Override
    public Product removeProduct(String productType) throws FlooringMasteryPersistenceException {
              return dao.removeProduct(productType);
    }
  
    
    private void validateProductData(Product product) throws
        FlooringMasteryDataValidationException {

          if (product.getcostPerSquareFoot()== null
            || product.getcostPerSquareFoot().trim().length() == 0

            || product.getlaborCostPerSquareFoot()== null
            || product.getlaborCostPerSquareFoot().trim().length() == 0                      
                                                   ) {
                   throw new  FlooringMasteryDataValidationException(
                     "ERROR: All fields are required.");
                 }
        }   

    @Override
    public Order updateOrderTotal(Order order)    
          throws FlooringMasteryPersistenceException,
                 FlooringMasteryDataValidationException  {
            
           validateOrderData(order);
        
        
           BigDecimal area    = new BigDecimal(order.getarea()).setScale(2, RoundingMode.HALF_UP);
           

           Product currentProduct = dao.getProduct(order.getproductType());
           if (currentProduct == null){
                throw new  FlooringMasteryDataValidationException(
                     "Product not on file!");
           }
           order.setcostPerSquareFoot(currentProduct.getcostPerSquareFoot());
           order.setlaborCostPerSquareFoot(currentProduct.getlaborCostPerSquareFoot());
           
           BigDecimal costPerSquareFoot = new BigDecimal(order.getcostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
           BigDecimal laborCostPerSquareFoot = new BigDecimal(order.getlaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
           

           Tax currentTax = dao.getTax(order.getstate());
           if (currentTax == null){
                throw new  FlooringMasteryDataValidationException(
                     "Can't sell to this State");
           }           

           order.settaxRate(currentTax.gettaxRate());
           
           BigDecimal taxRate = new BigDecimal(order.gettaxRate()).setScale(2, RoundingMode.HALF_UP);           
         
           BigDecimal materialCost  = area.multiply(costPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
           BigDecimal laborCost  = area.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
           
           BigDecimal subtotal =  materialCost.add(laborCost).setScale(2, RoundingMode.HALF_UP);
           BigDecimal taxpaid =  subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
           taxpaid = taxpaid.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
           BigDecimal total =  subtotal.add(taxpaid).setScale(2, RoundingMode.HALF_UP);
           
           order.setmaterialCost(materialCost.toString());
           order.setlaborCost(laborCost.toString());
           order.settax(taxpaid.toString());
           order.settotal(total.toString());
           return order;
    }

    @Override
    public void exportAllOrders() throws FlooringMasteryPersistenceException {
         dao.exportAlldata();      
    }
}
