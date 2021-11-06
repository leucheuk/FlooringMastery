/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.dao;

import com.sherlock.flooringmastery.dto.Order;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sherlock
 */
public class FlooringMasteryDaoFileImplTest {
    
    public static final String PROJECT_DIR = "c:\\repos\\GitHub\\classwork\\FlooringMastery";
    public String FLOORING_FILE_DIR ; 
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoFileImplTest() {
        
    }
    
 //   @BeforeAll
 //   public static void setUpClass() {
  //  }
    
//    @AfterAll
//    public static void tearDownClass() {
//    }
    
    @BeforeEach
    public void setUp()  throws Exception{
        String testFolder = "\\TestOrders\\";
        // Use the FileWriter to quickly blank the file
    //    new FileWriter(testFolder);
    //    testDao = new FlooringMasteryDaoFileImpl(testFolder);

       deleteAllOrderFiles(testFolder);
       testDao = new FlooringMasteryDaoFileImpl(testFolder);
    
    }
    
//    @AfterEach
//    public void tearDown() {
//    }

    @Test
    public void testAddGetOrder() throws FlooringMasteryPersistenceException {
        //Create our first order
        Order firstOrder = new Order("1");
        firstOrder.setOrderDate("12122021");
        firstOrder.setcustomerName("Albert Einstein");
        firstOrder.setstate("CA");
        firstOrder.settaxRate("25.00");
        firstOrder.setarea("249.00");
        firstOrder.setproductType("Tile");
        firstOrder.setcostPerSquareFoot("3.50");
        firstOrder.setmaterialCost("871.50");
        firstOrder.setlaborCostPerSquareFoot("4.15");
        firstOrder.setlaborCost("1033.35");
        firstOrder.settax("476.21");
        firstOrder.settotal("2381.06");
        
        Order secondOrder = new Order("2");
        secondOrder.setOrderDate("12162021");
        secondOrder.setcustomerName("Doctor Who");
        secondOrder.setstate("WA");
        secondOrder.settaxRate("9.25");
        secondOrder.setarea("243.00");
        secondOrder.setproductType("Wood");
        secondOrder.setcostPerSquareFoot("5.15");
        secondOrder.setmaterialCost("1251.45");
        secondOrder.setlaborCostPerSquareFoot("4.75");
        secondOrder.setlaborCost("1154.25");
        secondOrder.settax("216.51");
        secondOrder.settotal("2622.21");   
        
        // Add both our orders to the DAO
        testDao.addOrder(firstOrder.getorderNumber(), firstOrder);
        testDao.addOrder(secondOrder.getorderNumber(), secondOrder);
        
        // Retrieve the list of all orders within the DAO
        List<Order> allOrders = testDao.getAllOrders();

        // First check the general contents of the list
        assertNotNull(allOrders, "The list of orders must not null");
        assertEquals(2, allOrders.size(),"List of orders should have 2 orders.");        

        // Then the specifics
        assertTrue(testDao.getAllOrders().contains(firstOrder),
                "The list of orders should include Albert.");
        assertTrue(testDao.getAllOrders().contains(secondOrder),
            "The list of orders should include Doctor.");
    }

    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceException {
        //Create two new orders
        Order firstOrder = new Order("1");
        firstOrder.setOrderDate("12122021");
        firstOrder.setcustomerName("David Chu");
        firstOrder.setstate("CA");
        firstOrder.settaxRate("25.00");
        firstOrder.setarea("249.00");
        firstOrder.setproductType("Tile");
        firstOrder.setcostPerSquareFoot("3.50");
        firstOrder.setmaterialCost("871.50");
        firstOrder.setlaborCostPerSquareFoot("4.15");
        firstOrder.setlaborCost("1033.35");
        firstOrder.settax("476.21");
        firstOrder.settotal("2381.06");
        
        Order secondOrder = new Order("2");
        secondOrder.setOrderDate("12162021");
        secondOrder.setcustomerName("Josephine Chan");
        secondOrder.setstate("WA");
        secondOrder.settaxRate("9.25");
        secondOrder.setarea("243.00");
        secondOrder.setproductType("Wood");
        secondOrder.setcostPerSquareFoot("5.15");
        secondOrder.setmaterialCost("1251.45");
        secondOrder.setlaborCostPerSquareFoot("4.75");
        secondOrder.setlaborCost("1154.25");
        secondOrder.settax("216.51");
        secondOrder.settotal("2622.21");   
        
        // Add both our orders to the DAO
        testDao.addOrder(firstOrder.getorderNumber(), firstOrder);
        testDao.addOrder(secondOrder.getorderNumber(), secondOrder);
     
                // Get all the orders
   // List<Order> allOrders = testDao.getAllOrders();
        
    // remove the first order 
    Order removedOrder = testDao.removeOrder(firstOrder.getorderNumber());

    // Check that the correct object was removed.
    assertEquals(removedOrder, firstOrder, "The removed order should be David.");

        // Get all the orders
      List<Order> allOrders = testDao.getAllOrders();

    // First check the general contents of the list
    assertNotNull( allOrders, "All orders list should be not null.");
    assertEquals( 1, allOrders.size(), "All orders should only have 1 order.");


    // Remove the second order
    removedOrder = testDao.removeOrder(secondOrder.getorderNumber());
    // Check that the correct object was removed.
    assertEquals( removedOrder, secondOrder, "The removed order should be Josephine.");

    // retrieve all of the orders again, and check the list.
    allOrders = testDao.getAllOrders();

    // Check the contents of the list - it should be empty
    assertTrue( allOrders.isEmpty(), "The retrieved list of orders should be empty.");

    // Try to 'get' both orders by their old id - they should be null!
    Order retrievedOrder = testDao.getOrder(firstOrder.getorderNumber());
    assertNull(retrievedOrder, "David was removed, should be null.");
    
    retrievedOrder = testDao.getOrder(secondOrder.getorderNumber());
    assertNull(retrievedOrder, "Josephhine was removed, should be null.");
    }

    
    
    
    private void deleteAllOrderFiles(String testFolder) throws FlooringMasteryPersistenceException {
        
        FLOORING_FILE_DIR =  PROJECT_DIR + testFolder;
        
        PrintWriter out=null;
        
        File folder = new File(FLOORING_FILE_DIR);
      
        File[] listOfFiles = folder.listFiles();
        List<File> listOfOrders = new ArrayList<>();
        
        for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                  if(listOfFiles[i].getName().startsWith("Orders_")){

                    try {
                      out = new PrintWriter(new FileWriter(FLOORING_FILE_DIR + listOfFiles[i].getName()));
                    } catch (IOException e) {
                     throw new FlooringMasteryPersistenceException (
                        "Could not erase Order file.", e);
                        }
                    out.flush();
   
                }
            } 
        }
    }
    
}
