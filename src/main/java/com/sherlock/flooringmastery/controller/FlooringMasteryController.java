/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.controller;

import com.sherlock.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sherlock.flooringmastery.model.Order;
import com.sherlock.flooringmastery.model.Product;
import com.sherlock.flooringmastery.model.Tax;
import com.sherlock.flooringmastery.service.FlooringMasteryDataValidationException;
import com.sherlock.flooringmastery.service.FlooringMasteryDuplicateIdException;
import com.sherlock.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sherlock.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public class FlooringMasteryController {
    
    private FlooringMasteryView view;  
    private FlooringMasteryServiceLayer service;
   // private FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    
   // private UserIO io = new UserIOConsoleImpl();

    public FlooringMasteryController(FlooringMasteryServiceLayer service,FlooringMasteryView view){
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
       
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                         listOrders();
                        break;
                    case 2:
                        createOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                          exportData();
                          break;
/*
                    case 11:
                        listTaxes();
                        break;
                    case 12:
                        createTax();
                        break;                    
                    case 13:
                        viewTax();
                        break;                     
                    case 14:
                         removeTax();
                        break;

                    case 21:
                        listProducts();
                        break;
                    case 22:
                        createProduct();
                        break;                    
                    case 23:
                       viewProduct();
                        break;                     
                    case 24:
                         removeProduct();
                        break;  
*/
                   case 6:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }   // Switch
            }   //while
            exitMessage();
        }   catch (FlooringMasteryPersistenceException e) {
                   view.displayErrorMessage(e.getMessage());
                   }  
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createOrder() throws FlooringMasteryPersistenceException {
       view.displayCreateOrderBanner();
       boolean hasErrors = false;
       do{
           
           List<Product> productList = service.getAllProducts();  
           List<Tax> taxList = service.getAllTaxes();
           Order currentOrder = view.getNewOrderInfo(null,taxList,productList);
           
            try{
               service.updateOrderTotal(currentOrder); 
               view.displayOrderCont(currentOrder);
               hasErrors = false;
            } catch (FlooringMasteryDataValidationException e) {
                 hasErrors = true;
                  view.displayErrorMessage(e.getMessage());
            }
          if(!hasErrors)  
           if(view.proceed()){
              
                try {
                   service.createOrder(currentOrder);
                   view.displayOrderCreateSuccessBanner();
                   hasErrors = false;
                 } catch (FlooringMasteryDuplicateIdException | FlooringMasteryDataValidationException e) {
                  hasErrors = true;
                  view.displayErrorMessage(e.getMessage());
                }
           } else {hasErrors = false;}
           
       } while (hasErrors);
    }
    
    private void createTax() throws FlooringMasteryPersistenceException  {
       view.displayCreateTaxBanner();
       boolean hasErrors = false;
       do{
            Tax currentTax = view.getNewTaxInfo();
            try {
               service.createTax(currentTax);
               view.displayTaxCreateSuccessBanner();
               hasErrors = false;
             } catch (FlooringMasteryDuplicateIdException | FlooringMasteryDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }

       } while (hasErrors);
    }
        
    private void createProduct() throws FlooringMasteryPersistenceException {
       view.displayCreateProductBanner();
       boolean hasErrors = false;
       do{
            Product currentProduct = view.getNewProductInfo();
            try {
               service.createProduct(currentProduct);
               view.displayProductCreateSuccessBanner();
               hasErrors = false;
             } catch (FlooringMasteryDuplicateIdException | FlooringMasteryDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
       } while (hasErrors);
    }

    private void listOrders() throws FlooringMasteryPersistenceException  {
    view.displayDisplayAllOrdersBanner();
    String orderDate = view.getdateChoice();
    List<Order> orderList = service.getAllOrders(orderDate);
    view.displayOrderList(orderList);
}
    
    private void listTaxes() throws FlooringMasteryPersistenceException  {
    view.displayDisplayAllTaxesBanner();
    List<Tax> taxList = service.getAllTaxes();
    view.displayTaxList(taxList);
}

    private void listProducts() throws FlooringMasteryPersistenceException  {
    view.displayDisplayAllProductsBanner();
    List<Product> productList = service.getAllProducts();
    view.displayProductList(productList);
}
    
    private void viewOrder() throws FlooringMasteryPersistenceException  {
    view.displayDisplayOrderBanner();
    String orderNumber = view.getOrderNumberChoice();
    Order order = service.getOrder(orderNumber);
    view.displayOrder(order);
}
    
    private void editOrder() throws FlooringMasteryPersistenceException  {
    view.displayDisplayEditBanner();
    String orderNumber = view.getOrderNumberChoice();
    Order currentOrder = service.getOrder(orderNumber);
    
       boolean hasErrors = false;
       do{
           
           List<Product> productList = service.getAllProducts();  
           List<Tax> taxList = service.getAllTaxes();
           currentOrder = view.getNewOrderInfo(currentOrder,taxList,productList);
           
            try{
               service.updateOrderTotal(currentOrder); 
               view.displayOrderCont(currentOrder);
               hasErrors = false;
            } catch (      FlooringMasteryDataValidationException e) {
                 hasErrors = true;
                  view.displayErrorMessage(e.getMessage());
            }
          if(!hasErrors)
            if(view.proceed()){
              
                try {
                   service.replaceOrder(currentOrder);
                   view.displayOrderCreateSuccessBanner();
                   hasErrors = false;
                 } catch ( FlooringMasteryDataValidationException e) {
                  hasErrors = true;
                  view.displayErrorMessage(e.getMessage());
                }
           } else {hasErrors = false;}
           
       } while (hasErrors);
    
    
    }   
    
    
    private void viewTax() throws FlooringMasteryPersistenceException {
    view.displayDisplayTaxBanner();
    String state = view.getstateChoice();
    Tax tax = service.getTax(state);
    view.displayTax(tax);
}

    private void viewProduct() throws FlooringMasteryPersistenceException  {
    view.displayDisplayProductBanner();
    String productType = view.getproductChoice();
    Product product = service.getProduct(productType);
    view.displayProduct(product);
}
    
private void removeOrder() throws FlooringMasteryPersistenceException  {
    view.displayRemoveOrderBanner();
    String orderNumber = view.getOrderNumberChoice();
    Order removedOrder = null ;   
   
    Order order = service.getOrder(orderNumber);
    view.displayOrderCont(order);

    if(view.proceed()){    
         removedOrder = service.removeOrder(orderNumber);
       } 
    view.displayRemoveOrderResult(removedOrder);
}

private void removeTax() throws FlooringMasteryPersistenceException {
    view.displayRemoveTaxBanner();
    String state = view.getstateChoice();
    Tax removedTax = service.removeTax(state);
    view.displayRemoveTaxResult(removedTax);
}

private void removeProduct() throws FlooringMasteryPersistenceException {
    view.displayRemoveProductBanner();
    String productType = view.getproductChoice();
    Product removedProduct = service.removeProduct(productType);
    view.displayRemoveProductResult(removedProduct);
}

private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}

private void exportData()  throws FlooringMasteryPersistenceException {
    view.displayExportAllDataBanner();
    service.exportAllOrders();
    view.displayExportSuccessBanner();
}

    
}
