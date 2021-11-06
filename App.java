/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherlock.flooringmastery;

import com.sherlock.flooringmastery.controller.FlooringMasteryController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Sherlock
 */
public class App {
        public static void main(String[] args) {
  //  UserIO myIo = new UserIOConsoleImpl();
  //  FlooringMasteryView myView = new FlooringMasteryView(myIo);
  //  FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
  //  FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerFileImpl(myDao);
  //  FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
  
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
           ctx.getBean("controller", FlooringMasteryController.class);
  
  
  controller.run();
    }   
}
