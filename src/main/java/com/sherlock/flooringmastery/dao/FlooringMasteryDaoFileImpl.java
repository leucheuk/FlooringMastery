/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dao;

import com.sherlock.flooringmastery.model.Order;
import com.sherlock.flooringmastery.model.Product;
import com.sherlock.flooringmastery.model.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**11
 *
 * @author Sherlock
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    public static final String PROJECT_DIR = "c:\\repos\\GitHub\\classwork\\FlooringMastery";
    public static final String FLOORING_FILE_DIR = PROJECT_DIR + "\\Orders\\";
    public static final String TAX_FILE = PROJECT_DIR + "\\Data\\Taxes.txt";
    public static final String PRODUCT_FILE = PROJECT_DIR + "\\Data\\Products.txt";
    public static final String DATAEXPORT_FILE = PROJECT_DIR + "\\Backup\\DataExport.txt";
     
    public static final String DELIMITER = ",";
    
    
    
    private Map<String, Order> orders = new HashMap<>();
    
    private Map<String, Tax> taxes = new HashMap<>();
    
    private Map<String, Product> products = new HashMap<>();
    
    /**
     *
     * @param orderNumber
     * @param order
     * @return
     * @throws FlooringMasteryPersistenceException
     */
    @Override
    public Order addOrder(String orderNumber, Order order) 
              throws FlooringMasteryPersistenceException{
                 loadFlooring();
                 Order newOrder = orders.put(orderNumber, order);
                 writeFlooring();
                 return newOrder;
        }

    @Override
    public List<Order> getAllOrders()
       throws FlooringMasteryPersistenceException{
        loadFlooring();
        return new ArrayList<Order>(orders.values());
    }

    public List<Order> getAllOrdersMem()
       throws FlooringMasteryPersistenceException{
        return new ArrayList<Order>(orders.values());
    }
    
    
    @Override
    public Order getOrder(String orderNumber)
      throws FlooringMasteryPersistenceException {
       loadFlooring();
       return orders.get(orderNumber);
    }

    @Override
    public Order removeOrder(String orderNumber) 
     throws FlooringMasteryPersistenceException {
         loadFlooring();
         Order removedOrder = orders.remove(orderNumber);
         writeFlooring();        
         return removedOrder;
    }

    @Override
    public Tax addTax(String state, Tax tax)
                 throws FlooringMasteryPersistenceException{
        loadTax();
        Tax newTax = taxes.put(state, tax);
        writeTax();
        return newTax; 
    }

    @Override
    public List<Tax> getAllTaxes() 
        throws FlooringMasteryPersistenceException  {
        loadTax();
        return new ArrayList<Tax>(taxes.values());
    }

    @Override
    public Tax getTax(String state) 
      throws FlooringMasteryPersistenceException {
         loadTax();
          return taxes.get(state);
    }

    @Override
    public Tax removeTax(String state)
     throws FlooringMasteryPersistenceException {
        loadTax();      
        Tax removedTax = taxes.remove(state);
        writeTax();
        return removedTax;
    }

    @Override
    public Product addProduct(String productType, Product product) 
           throws FlooringMasteryPersistenceException  {
        loadProduct();
        Product newProduct = products.put(productType, product);
        writeProduct();
        return newProduct; 
    }

    @Override
    public List<Product> getAllProducts() 
     throws FlooringMasteryPersistenceException  {
         loadProduct();
          return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getProduct(String productType)
      throws FlooringMasteryPersistenceException {
        loadProduct();
        return products.get(productType);
    }

    @Override
    public Product removeProduct(String productType)
     throws FlooringMasteryPersistenceException {
       loadProduct();   
       Product removedProduct = products.remove(productType);
       writeProduct();
       return removedProduct;
    }
    
    private Order unmarshallOrder(String orderAsText){
        
        String[] orderTokens = orderAsText.split(DELIMITER);

        String orderNumber = orderTokens[0];

        Order orderFromFile = new Order(orderNumber);

        orderFromFile.setcustomerName(orderTokens[1]);
        orderFromFile.setstate(orderTokens[2]);
        orderFromFile.settaxRate(orderTokens[3]);
        orderFromFile.setproductType(orderTokens[4]);
        orderFromFile.setarea(orderTokens[5]);
        orderFromFile.setcostPerSquareFoot(orderTokens[6]);
        orderFromFile.setlaborCostPerSquareFoot(orderTokens[7]);
        orderFromFile.setmaterialCost(orderTokens[8]);
        orderFromFile.setlaborCost(orderTokens[9]);
        orderFromFile.settax(orderTokens[10]);
        orderFromFile.settotal(orderTokens[11]);       
        //orderFromFile.setOrderDate(orderTokens[12]);       
               
        return orderFromFile;
    }
    
       private Tax unmarshallTax(String taxAsText){
        
        String[] taxTokens = taxAsText.split(DELIMITER);

        String state = taxTokens[0];

        Tax taxFromFile = new Tax(state);

        taxFromFile.setstateName(taxTokens[1]);
        taxFromFile.settaxRate(taxTokens[2]); 
     
        return taxFromFile;
    }
    
    private Product unmarshallProduct(String productAsText){
        
        String[] productTokens = productAsText.split(DELIMITER);

        String productType = productTokens[0];

        Product productFromFile = new Product(productType);

        productFromFile.setcostPerSquareFoot(productTokens[1]);
        productFromFile.setlaborCostPerSquareFoot(productTokens[2]); 
     
        return productFromFile;
    }
    
    private void loadFlooring() throws FlooringMasteryPersistenceException {
       
        Scanner scanner = null;

        List<File> orderFiles = listAllOrderFiles(); 

        if(orderFiles.size()==0){
           return;
        }
            
        
        for(int i=0; i< orderFiles.size(); i++){
            String flooringFile = FLOORING_FILE_DIR + orderFiles.get(i).getName();
            String currentldAsText= orderFiles.get(i).getName().substring(7,15);
 //           System.out.println(currentldAsText);
            
            try { // Create Scanner for reading the file
                 scanner = new Scanner(
                        new BufferedReader(
                             new FileReader(flooringFile /*FLOORING_FILE*/)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException(
                        "-_- Could not load flooring data into memory.", e);
            }
            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentStudent holds the most recent student unmarshalled
            Order currentOrder;

            if(scanner.hasNextLine()){
              currentLine = scanner.nextLine(); //ignore title line
            }
          
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into an Order object
                currentOrder = unmarshallOrder(currentLine);
                currentOrder.setOrderDate(currentldAsText);
                
                orders.put(currentOrder.getorderNumber(), currentOrder);
            }
        }
       // close scanner
      scanner.close();
    }
   
        private void loadTax() throws FlooringMasteryPersistenceException {
            Scanner scanner;

            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(TAX_FILE)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException(
                        "-_- Could not load tax data into memory.", e);
            }
            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentTax holds the most recent tax unmarshalled
            Tax currentTax;
            // Go through TAX_FILE line by line, decoding each line into a 
            // Tax object by calling the unmarshallTax method.
            // Process while we have more lines in the file
            
             
            if(scanner.hasNextLine()){
              currentLine = scanner.nextLine(); //ignore title line
            }
            
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into a Tax object
                currentTax = unmarshallTax(currentLine);

                // We are going to use the state abbr as the map key for our tax object.
                // Put currentTax into the map using state abbreviation as the key
                taxes.put(currentTax.getstate(), currentTax);
            }
            // close scanner
            scanner.close();
    }

        private void loadProduct() throws FlooringMasteryPersistenceException {
            Scanner scanner;

            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(PRODUCT_FILE)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException(
                        "-_- Could not load product data into memory.", e);
            }
            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentTax holds the most recent tax unmarshalled
            Product currentProduct;
            // Go through PRODUCT_FILE line by line, decoding each line into a 
            // Product object by calling the unmarshallProduct method.
            // Process while we have more lines in the file
            if(scanner.hasNextLine()){
               currentLine = scanner.nextLine(); //ignore title line
               }
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // unmarshall the line into a Product object
                currentProduct = unmarshallProduct(currentLine);

                // We are going to use the product type as the map key for our product object.
                // Put currentProduct into the map using product type as the key
                products.put(currentProduct.getproductType(), currentProduct);
            }
            // close scanner
            scanner.close();
    }

    private String marshallOrder(Order anOrder){

        String orderAsText = anOrder.getorderNumber()+ DELIMITER;
        orderAsText += anOrder.getcustomerName()+ DELIMITER;
        orderAsText += anOrder.getstate()+ DELIMITER;
        orderAsText += anOrder.gettaxRate()+ DELIMITER;   
        orderAsText += anOrder.getproductType()+ DELIMITER;
        orderAsText += anOrder.getarea()+ DELIMITER;
        orderAsText += anOrder.getcostPerSquareFoot()+ DELIMITER;
        orderAsText += anOrder.getlaborCostPerSquareFoot()+ DELIMITER;
        orderAsText += anOrder.getmaterialCost()+ DELIMITER;
        orderAsText += anOrder.getlaborCost()+ DELIMITER;
        orderAsText += anOrder.gettax()+ DELIMITER;
        orderAsText += anOrder.gettotal();
        return orderAsText;
    }
    
    private String marshallTax(Tax aTax){

        String taxAsText = aTax.getstate()+ DELIMITER;

        taxAsText += aTax.getstateName()+ DELIMITER;

        taxAsText += aTax.gettaxRate();

        return taxAsText;
    }
    
    private String marshallProduct(Product aProduct){

        String productAsText = aProduct.getproductType()+ DELIMITER;

        productAsText += aProduct.getcostPerSquareFoot()+ DELIMITER;

        productAsText += aProduct.getlaborCostPerSquareFoot();

        return productAsText;
    }
    
   private void writeFlooring() throws FlooringMasteryPersistenceException {

        PrintWriter out = null;
        
        String orderAsText;
        List<Order> orderList = this.getAllOrdersMem();
        String prevld = "";
        String currentld;
        
        List<File> orderFiles = listAllOrderFiles();
        
        deleteAllOrderFiles();
        
        for (Order currentOrder : orderList) {
                    
            currentld=currentOrder.getOrderDate();
            if(currentld.compareTo(prevld)!=0  ){   
                try {
                      out = new PrintWriter(new FileWriter(FLOORING_FILE_DIR+"Orders_"+currentld+".txt"));
                    } catch (IOException e) {
                    throw new FlooringMasteryPersistenceException(
                        "Could not save Flooring data.", e);
                        }
                
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
                out.flush();
              }
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
            prevld = currentld;
        }
         
        
        /*
        try {
            out = new PrintWriter(new FileWriter(FLOORING_FILE_DIR+"xOrders_.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save Flooring data.", e);
        }

        String orderAsText;
        List<Order> orderList = this.getAllOrders();
        
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        out.flush(); //write title and fkush        
        
        for (Order currentOrder : orderList) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        
        */
        
        // Clean up
        out.close();
    } 
   
      private void writeTax() throws FlooringMasteryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TAX_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save Tax data.", e);
        }

        String taxAsText;
        List<Tax> taxList = this.getAllTaxes();
        
        out.println("State,StateName,TaxRate");
        out.flush();
        
        for (Tax currentTax : taxList) {
            taxAsText = marshallTax(currentTax);
            out.println(taxAsText);
            out.flush();
        }
        // Clean up
        out.close();
    } 
    
      private void writeProduct() throws FlooringMasteryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCT_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save Product data.", e);
        }

        String productAsText;
        List<Product> productList = this.getAllProducts();
        
        out.println("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot");
        out.flush();
        
        for (Product currentProduct : productList) {
            productAsText = marshallProduct(currentProduct);
            out.println(productAsText);
            out.flush();
        }
        // Clean up
        out.close();
    } 
      
    private List<File> listAllOrderFiles (){
        File folder = new File(FLOORING_FILE_DIR);
        File[] listOfFiles = folder.listFiles();
        List<File> listOfOrders = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                  if(listOfFiles[i].getName().startsWith("Orders_")){
                    listOfOrders.add(listOfFiles[i]);  
//                    System.out.println("File " + listOfFiles[i].getName());
                  }
                } 
            }
        return listOfOrders;
      }
    
    private void deleteAllOrderFiles  () throws FlooringMasteryPersistenceException {
        
        PrintWriter out=null;
        
        File folder = new File(FLOORING_FILE_DIR);
        File[] listOfFiles = folder.listFiles();
        List<File> listOfOrders = new ArrayList<>();
        
        for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                  if(listOfFiles[i].getName().startsWith("Orders_")){
 /* 
                      File myFile = new File(FLOORING_FILE_DIR + listOfFiles[i].getName());
                      myFile 
                      if (myFile.delete()){
                          System.out.println("file deleted");
                      }
 */                     
                    try {
                      out = new PrintWriter(new FileWriter(FLOORING_FILE_DIR + listOfFiles[i].getName()));
                    } catch (IOException e) {
                     throw new FlooringMasteryPersistenceException (
                        "Could not delete Order file.", e);
                        }
                    
                    // out.println("");
                    out.flush();
                    //  System.out.println("File: " + listOfFiles[i].getName());
                }
            } 
        }
   }
    
    @Override
    public void exportAlldata() throws FlooringMasteryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DATAEXPORT_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not export data file.", e);
        }
        
        String orderAsText;
        List<Order> orderList = this.getAllOrders();
        
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                + "LaborCost,Tax,Total,OrderDate");
        out.flush();
        
        for (Order currentOrder : orderList) {
            orderAsText = marshallOrder(currentOrder) 
                    + ","
                    +  currentOrder.getOrderDate().substring(0, 2)
                    + "-"
                    + currentOrder.getOrderDate().substring(2, 4)
                    + "-"
                    + currentOrder.getOrderDate().substring(4, 8);
            out.println(orderAsText);
            out.flush();
        }
        
        
    }
      
}
