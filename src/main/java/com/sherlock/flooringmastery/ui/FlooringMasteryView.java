/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.ui;

import com.sherlock.flooringmastery.model.Order;
import com.sherlock.flooringmastery.model.Product;
import com.sherlock.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Sherlock
 */
public class FlooringMasteryView {
    
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100.00);
    
    private UserIO io;
    
    public FlooringMasteryView(UserIO io) {
    this.io = io;
}

    public int printMenuAndGetSelection() {
            io.print("<<Flooring Program>>");
            io.print("1. Display Orders");
            io.print("2. Add an Order");
            io.print("3. Edit an Order");
            io.print("4. Remove an Order");
            io.print("5. Export All Data");
            io.print("99. Quit");
            
            io.print("11. Display Taxes");
//            io.print("12. Add a Tax");
//            io.print("13. Edit a Tax");
//            io.print("14. Remove a Tax");

            io.print("21. Display Products");
//            io.print("22. Add an Product");
//            io.print("23. Edit an Product");
//            io.print("24. Remove an Product");
            

            
        return io.readInt("Please select from the above choices.", 1, 99);
    }
    
  public Order getNewOrderInfo(Order editOrder,  List<Tax> txLst, List<Product> prdctLst) {
    Boolean editmode = false;
    String orderNumber= null;
    String orderDate =  null;
    String customerName =  null ;
    String state = null;
    String productType = null;
    String area = null;
    
    if (editOrder != null) 
        editmode = true;
    
    boolean isValid = false;
    
    if (editmode){
        orderNumber = editOrder.getorderNumber();
    } else {
            //String orderNumber = io.readString("Please enter Order Number ");
            do {
                try {
                       orderNumber = io.readString("Please enter Order Number ");
                       int number = Integer.parseInt(orderNumber);
                            if(number>0){
                               isValid = true;
                              } else{
                               System.out.println("Order number should be a positive integer!");
                              }
                       } catch(NumberFormatException ex) {
                         System.out.println("That was not a valid number!");
                     }
                } while(!isValid);    
           }
    
        if(editmode) {
           orderDate =  editOrder.getOrderDate();
        }        
        else {
    
                isValid = false;    
           //  orderDate = io.readString("Please enter Order Date ");
                 do {
                         try{  
                          LocalDate ld1 = LocalDate.now();

                          orderDate = io.readString("Please enter Order Date MM/dd/yyyy ");
                          LocalDate ld2 = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));   
                          Period diff = ld2.until(ld1); // 
                          if(diff.isNegative() || diff.isZero()){
                                  isValid = true;  
                          } else{
                                    System.out.println("Order date must be in the future");
                                   }
                         } catch (DateTimeParseException ex){
                               System.out.println("That was not a valid date!");
                           }
                    } while(!isValid); 

                 orderDate = orderDate.substring(0,2)  //MM
                             +orderDate.substring(3,5)  //DD
                             +orderDate.substring(6,10);   //yyyy     
           }
        
      isValid = false;
  //  customerName = io.readString("Please enter Customer Name ");
            do {
                if(editmode){
                  customerName = editOrder.getcustomerName();
                  customerName = io.readString("Please enter Customer Name ("+customerName+") ");
                } else {
                  customerName = io.readString("Please enter Customer Name ");
                }
                if(customerName == null || customerName.isEmpty()) {
                    if(editmode){
                        System.out.println("Customer name not changed.");
                        customerName = editOrder.getcustomerName();
                        isValid = true;
                    } else
                    System.out.println("You did not enter anything!");
                    
                } else {
                    isValid = true;
                }
            } while(!isValid);
            
    isValid = false;
    //tate = io.readString("Please enter State ");
             do {
                 
                if(editmode){
                  state =  editOrder.getstate();
                  state = io.readString("Please enter State Abbreviation ("+state+") ");
                  } else {
                  state = io.readString("Please enter State Abbreviation ");
                  }

                if(state == null || state.isEmpty() || state.length()!=2) {
                    if(editmode) {
                         state =  editOrder.getstate();
                        System.out.println("State abbr not changed");                        
                         isValid = true;                         
                    } else {
                      System.out.println("You did not enter correct State abbr!");
                    }
                } else {
                     for (Tax currentTax : txLst) {
                        if(currentTax.getstate().equals(state)) {
                            isValid = true; 
                        }
                     } 
                    if(!isValid){
                     System.out.println("State not on file!");
                     if(editmode){
                        state =  editOrder.getstate();
                        System.out.println("State abbr. not changed");                        
                        isValid = true;   
                     }
                     else {                       
                         
                         return new Order(orderNumber);
                     }
                    }  
                }
            } while(!isValid);
            
 //   String taxRate = io.readString("Please enter Tax Rate ");
    
    isValid = false;
    
    displayProductListCont(prdctLst);
    
    //productType = io.readString("Please enter Product Type ");
        do {
               if(editmode){
                    productType =  editOrder.getproductType();
                    productType = io.readString("Please enter Product type ("+productType+") ");
                  } else {
                    productType = io.readString("Please enter Product type ");
                  }

                if(productType == null || productType.isEmpty()) {
                    
                   if(editmode) {
                         productType =  editOrder.getproductType();
                         System.out.println("Product type not changed");                        
                         isValid = true;                         
                    } else {            
                    System.out.println("You did not enter correct Product Type!");
                   }
                } else {
                    isValid = true;
                }
            } while(!isValid);
    
    isValid = false;
  //  String area = io.readString("Please enter area in square foot ");
        do {
            try{
  
                 if(editmode){
                  area =  editOrder.getarea();
                  area = io.readString("Please enter area in square foot ("+area+") ");
                  } else {
                  area = io.readString("Please enter area in square foot ");
                  }
            
            if(area == null || area.isEmpty()){
                if(editmode){
                    area = editOrder.getarea(); 
                  }          
               }
              BigDecimal number = new BigDecimal(area);
              area = number.setScale(2, RoundingMode.HALF_UP).toString();
                    if(number.compareTo(ONE_HUNDRED) == -1 ){
                        System.out.println("min. area 100 sq foot!");
                        if(editmode) {
                            area =  editOrder.getarea();    
                          }   
                      }else{
                       isValid = true;
                      }
               } catch(NumberFormatException ex) {
                 System.out.println("That was not a valid number!");
             }
            } while(!isValid);    
    
    
    
//    String costPerSquareFoot = io.readString("Please enter Cost per Square Foot ");
//    String laborCostPerSquareFoot = io.readString("Please enter Labor Cost Per Square Foot ");
 
    Order currentOrder = new Order(orderNumber);
    currentOrder.setOrderDate(orderDate);
    currentOrder.setcustomerName(customerName);
    
    currentOrder.setstate(state);
//    currentOrder.settaxRate(taxRate);
    
    currentOrder.setproductType(productType);
    currentOrder.setarea(area);
//    currentOrder.setcostPerSquareFoot(costPerSquareFoot);
//    currentOrder.setlaborCostPerSquareFoot(laborCostPerSquareFoot);
              
    return currentOrder;
}
    
  public void displayCreateOrderBanner() {
    io.print("=== Create Order ===");
   }
  public void displayOrderCreateSuccessBanner() {
    io.readString(
        "Order successfully created.  Please hit enter to continue");
    }
  
    public Tax getNewTaxInfo() {
    String state = io.readString("Please enter State Abbrev");
    String stateName = io.readString("Please enter full State Name");
    String taxRate = io.readString("Please enter Tax Rate");    
    
    Tax currentTax = new Tax(state);
    currentTax.setstateName(stateName);
    currentTax.settaxRate(taxRate);
    return currentTax;
    }
   public void displayCreateTaxBanner() {
    io.print("=== Create Tax ===");
   }
  public void displayTaxCreateSuccessBanner() {
    io.readString(
        "Tax successfully created.  Please hit enter to continue");
    } 
  
    public Product getNewProductInfo() {
    String productType = io.readString("Please enter Product Type");
    String costPerSquareFoot = io.readString("Please enter Cost Per Square Foot");
    String laborCostPerSquareFoot = io.readString("Please enter Labor Cost Per Square Foot");    
    
    Product currentProduct = new Product(productType);
    currentProduct.setcostPerSquareFoot(costPerSquareFoot);
    currentProduct.setlaborCostPerSquareFoot(laborCostPerSquareFoot);
    return currentProduct;
    }
   public void displayCreateProductBanner() {
    io.print("=== Create Product ===");
   }
  public void displayProductCreateSuccessBanner() {
    io.readString(
        "Product successfully created.  Please hit enter to continue");
    }   
    
  public void displayOrderList(List<Order> orderList) {
    if(orderList.isEmpty()){
        io.print("No order on that date!");
    } else { 
 
     io.print("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
     orderList.stream().sorted(Comparator.comparing(Order::getorderNumber)).forEach((p) -> System.out.println(
     String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
              p.getorderNumber(),   
              p.getcustomerName(),
              p.getstate(),
              p.gettaxRate(),
              p.getproductType(),
              p.getarea(),
              p.getcostPerSquareFoot(),
              p.getlaborCostPerSquareFoot(),
              p.getmaterialCost(),
              p.getlaborCost(),
              p.gettax(),
              p.gettotal())            
            ));
 

        
   /*     
        io.print("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        for (Order currentOrder : orderList) {
            String orderInfo = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
//                currentOrder.getOrderDate(),
                currentOrder.getorderNumber(),
                currentOrder.getcustomerName(),
                currentOrder.getstate(),            
                currentOrder.gettaxRate(),     
                currentOrder.getproductType(),
                currentOrder.getarea(),
                currentOrder.getcostPerSquareFoot(),
                currentOrder.getlaborCostPerSquareFoot(),
                currentOrder.getmaterialCost(),
                currentOrder.getlaborCost(),
                currentOrder.gettax(),
                currentOrder.gettotal());
            io.print(orderInfo);
           }
        }  
    */
    
    
      io.readString("Please hit enter to continue.");
    }
}
  
  public void displayTaxList(List<Tax> taxList) {
    io.print("State,StateName,TaxRate");
      for (Tax currentTax : taxList) {
        String taxInfo = String.format("%s,%s,%s",
              currentTax.getstate(),
              currentTax.getstateName(),
              currentTax.gettaxRate());
        io.print(taxInfo);
    }
    io.readString("Please hit enter to continue.");
}
  
  public void displayProductList(List<Product> productList) {
 /*   io.print("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot");  
    for (Product currentProduct : productList) {
        String productInfo = String.format("%s,%s,%s",
              currentProduct.getproductType() ,
              currentProduct.getcostPerSquareFoot(),
              currentProduct.getlaborCostPerSquareFoot());
        io.print(productInfo);
    }
   */
    displayProductListCont(productList);
    io.readString("Please hit enter to continue.");
   }

    
    public void displayProductListCont(List<Product> productList) {
    io.print("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot");  
    for (Product currentProduct : productList) {
        String productInfo = String.format("%s,%s,%s",
              currentProduct.getproductType() ,
              currentProduct.getcostPerSquareFoot(),
              currentProduct.getlaborCostPerSquareFoot());
        io.print(productInfo);
    }
   }
  

  public void displayDisplayAllOrdersBanner() {
    io.print("=== Display All Orders ===");
}

  public void displayDisplayAllTaxesBanner() {
    io.print("=== Display All Taxes ===");
}

  public void displayDisplayAllProductsBanner() {
    io.print("=== Display All Products ===");
}

  
  public void displayDisplayOrderBanner () {
    io.print("=== Display Order ===");
}

  

  public void displayDisplayEditBanner () {
    io.print("=== Edit Order ===");
}
          
          
public String getOrderNumberChoice() {
    return io.readString("Please enter the Order Number");
}



public void displayOrder(Order order) {
    displayOrderCont(order);
    io.readString("Please hit enter to continue.");
}

public void displayOrderCont(Order order){
    
  if (order != null) {
        String ld = order.getOrderDate();
        io.print("Order number: " + order.getorderNumber());
//        io.print("Order date: " + order.getOrderDate());
        io.print("Order date: " + ld.substring(0, 2)+"-"+ld.substring(2, 4)+"-"+ld.substring(4));
        io.print("Customer name: " + order.getcustomerName());
        io.print("State: " + order.getstate()); 
        io.print("Tax rate: " + order.gettaxRate());           
    
        io.print("Product type: " + order.getproductType());     
        io.print("Area: " + order.getarea()); 
        io.print("Cost per sq.foot: $" + order.getcostPerSquareFoot()); 
         io.print("Labor cost per sq.foot: $" + order.getlaborCostPerSquareFoot());     
        
        io.print("Material cost: $" + order.getmaterialCost());     
        io.print("Labor cost: $" + order.getlaborCost());  
        io.print("Tax : $" + order.gettax()); 
        io.print("Total cost : $" + order.gettotal()); 
        io.print("");
    } else {
        io.print("No such Order.");
    } 
}

  
  public void displayDisplayTaxBanner () {
    io.print("=== Display State Tax Rate ===");
}

public String getstateChoice() {
    return io.readString("Please enter the State Abbreviation:");
}

public void displayTax(Tax tax) {
    if (tax != null) {
        io.print("State Abbreviation: " + tax.getstate());
        io.print("State name: " + tax.getstateName());
        io.print("Tax rate: " + tax.gettaxRate() + "%");
        io.print("");
    } else {
        io.print("No such state info.");
    }
    io.readString("Please hit enter to continue.");
}
  
  public void displayDisplayProductBanner () {
    io.print("=== Display Product info ===");
}

public String getproductChoice() {
    return io.readString("Please enter the product name: ");
}

public String getdateChoice() {
    return io.readString("Please enter the order date (MM/dd/yyyy): ");
}

public void displayProduct(Product product) {
    if (product != null) {
        io.print("Product Type: " + product.getproductType());
        io.print("Cost PerSquare Foot : $" + product.getcostPerSquareFoot());
        io.print("Labor Cost Per Square Foot : " + product.getlaborCostPerSquareFoot());
        io.print("");
    } else {
        io.print("No such product info.");
    }
    io.readString("Please hit enter to continue.");
}
  
public void displayRemoveOrderBanner () {
    io.print("=== Remove Order ===");
}

public void displayRemoveOrderResult(Order orderRecord) {
    if(orderRecord != null){
      io.print("Order successfully removed.");
    }else{
      io.print("Order not removed.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayRemoveTaxBanner () {
    io.print("=== Remove Tax ===");
}

public void displayRemoveTaxResult(Tax taxRecord) {
    if(taxRecord != null){
      io.print("State/Tax successfully removed.");
    }else{
      io.print("No such State/Tax.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayRemoveProductBanner () {
    io.print("=== Remove Product ===");
}

public void displayRemoveProductResult(Product productRecord) {
    if(productRecord != null){
      io.print("Product successfully removed.");
    }else{
      io.print("No such product.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}

public void displayExportAllDataBanner() {
    io.print("Export All Data");
}

public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}

public void displayExportSuccessBanner() {
     io.print("All data successfully exported.");
     io.readString("Please hit enter to continue."); 
    }

 public Boolean proceed() {
    String cntn =io.readString("Enter Y/y to confirm: "); 
     return (cntn.equals("Y") || cntn.equals("y"));
    }
}



