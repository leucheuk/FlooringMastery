/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery.service;

import com.sherlock.flooringmastery.dao.FlooringMasteryDao;
import com.sherlock.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sherlock.flooringmastery.dto.Order;
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
public class FlooringMasteryServiceLayerFileImplTest {
    
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerFileImplTest() {
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        service = new FlooringMasteryServiceLayerFileImpl(dao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

   
    
    @Test
        public void testCreateValidOrder() {
            // ARRANGE
            Order order = new Order("2");
            order.setOrderDate("12122021");
            order.setcustomerName("David Chu");
            order.setstate("CA");
            order.settaxRate("25.00");
            order.setarea("249.00");
            order.setproductType("Tile");
            order.setcostPerSquareFoot("3.50");
            order.setmaterialCost("871.50");
            order.setlaborCostPerSquareFoot("4.15");
            order.setlaborCost("1033.35");
            order.settax("476.21");
            order.settotal("2381.06");    
            // ACT
            try {
                service.createOrder(order);
            } catch (FlooringMasteryDuplicateIdException
                    | FlooringMasteryDataValidationException
                    | FlooringMasteryPersistenceException e) {
            // ASSERT
                fail("Student was valid. No exception should have been thrown.");
            }
        }
    
        
     @Test
        public void testCreateDuplicateOrder() {
            // ARRANGE
            Order order = new Order("1");
            order.setOrderDate("12122021");
            order.setcustomerName("David Chu");
            order.setstate("CA");
            order.settaxRate("25.00");
            order.setarea("249.00");
            order.setproductType("Tile");
            order.setcostPerSquareFoot("3.50");
            order.setmaterialCost("871.50");
            order.setlaborCostPerSquareFoot("4.15");
            order.setlaborCost("1033.35");
            order.settax("476.21");
            order.settotal("2381.06");    

            // ACT
            try {
                service.createOrder(order);
                fail("Expected DupeId Exception was not thrown.");
            } catch (FlooringMasteryDataValidationException
                    | FlooringMasteryPersistenceException e) {
            // ASSERT
                fail("Incorrect exception was thrown.");
            } catch (FlooringMasteryDuplicateIdException e){
                return;
            }
        }   
    
        
    @Test
        public void testCreateOrderInvalidData() throws Exception {
            // ARRANGE
            Order order = new Order("0002");
            order.setOrderDate("12122021");
            order.setcustomerName("David Chu");
            order.setstate("CA");
            order.settaxRate("25.00");
            order.setarea("");           //invalidate
            order.setproductType("Tile");
            order.setcostPerSquareFoot("3.50");
            order.setmaterialCost("871.50");
            order.setlaborCostPerSquareFoot("4.15");
            order.setlaborCost("1033.35");
            order.settax("476.21");
            order.settotal("2381.06");   

            // ACT
            try {
                service.createOrder(order);
                fail("Expected ValidationException was not thrown.");
            } catch (FlooringMasteryDuplicateIdException
                    | FlooringMasteryPersistenceException e) {
            // ASSERT
                fail("Incorrect exception was thrown.");
            } catch (FlooringMasteryDataValidationException e){
                return;
            }  
        }
        
    @Test
    public void testGetAllOrders() throws Exception {
        // ARRANGE
        Order testClone = new Order("1");
            testClone.setOrderDate("12122021");
            testClone.setcustomerName("David Chu");
            testClone.setstate("CA");
            testClone.settaxRate("25.00");
            testClone.setarea("");           //invalidate
            testClone.setproductType("Tile");
            testClone.setcostPerSquareFoot("3.50");
            testClone.setmaterialCost("871.50");
            testClone.setlaborCostPerSquareFoot("4.15");
            testClone.setlaborCost("1033.35");
            testClone.settax("476.21");
            testClone.settotal("2381.06");  

        // ACT & ASSERT
        assertEquals( 1, service.getAllOrders("12122021").size(), 
                                       "Should only have one student.");
        assertTrue( service.getAllOrders("12122021").contains(testClone),
                                  "The one student should be Ada.");
    }      
     
    @Test
    public void testGetOrder() throws Exception {
        // ARRANGE
        Order testClone = new Order("0001");
            testClone.setOrderDate("12122021");
            testClone.setcustomerName("Ada");
            testClone.setstate("CA");
            testClone.settaxRate("25.00");
            testClone.setarea("123"); 
            testClone.setproductType("Tile");
            testClone.setcostPerSquareFoot("3.50");
            testClone.setmaterialCost("871.50");
            testClone.setlaborCostPerSquareFoot("4.15");
            testClone.setlaborCost("1033.35");
            testClone.settax("476.21");
            testClone.settotal("2381.06");  

        // ACT & ASSERT
        Order shouldBeAda = service.getOrder("0001");
        assertNotNull(shouldBeAda, "Getting 0001 should be not null.");
        assertEquals( testClone, shouldBeAda,
                                       "Order stored under 0001 should be Ada.");

        Order shouldBeNull = service.getOrder("0042");    
        assertNull( shouldBeNull, "Getting 0042 should be null.");

    }  
    
    @Test
    public void testRemoveStudent() throws Exception {
        // ARRANGE
        Order testClone = new Order("0001");
            testClone.setOrderDate("12122021");
            testClone.setcustomerName("Ada");
            testClone.setstate("CA");
            testClone.settaxRate("25.00");
            testClone.setarea("232");  
            testClone.setproductType("Tile");
            testClone.setcostPerSquareFoot("3.50");
            testClone.setmaterialCost("871.50");
            testClone.setlaborCostPerSquareFoot("4.15");
            testClone.setlaborCost("1033.35");
            testClone.settax("476.21");
            testClone.settotal("2381.06");  

        // ACT & ASSERT
        Order shouldBeAda = service.removeOrder("0001");
        assertNotNull( shouldBeAda, "Removing 0001 should be not null.");
        assertEquals( testClone, shouldBeAda, "Student removed from 0001 should be Ada.");

        Order shouldBeNull = service.removeOrder("0042");    
        assertNull( shouldBeNull, "Removing 0042 should be null.");

    }
    
    
}
