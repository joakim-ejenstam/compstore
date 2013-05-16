/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author Martin
 */
public class ShoppingCartBean {
    
    private Collection shoppingCart;
    
    public ShoppingCartBean() {
        shoppingCart = new ArrayList();
    }
    
    public void addItem(ComputerBean cb, int quan) {
        Object newItem[] = null;
        ComputerBean tmpBean = null;
        
        if (shoppingCart.isEmpty()) {
            newItem = new Object[2];
            newItem[0] = cb;
            newItem[1] = new Integer(quan);    
            shoppingCart.add(newItem);
        } else {
            Iterator iter = shoppingCart.iterator();
            Object arr[];
	    boolean found = false;
            
            while (iter.hasNext()) {
                arr = (Object[])iter.next();
                
                if (((ComputerBean)arr[0]).getID() == cb.getID()) {
                    Integer antal = (Integer)arr[1];
		    arr[1] = new Integer(antal.intValue()+quan); 
		    found = true;
                }
            }
            
            if(!found){
		newItem = new Object[2];
		newItem[0]=cb;
		newItem[1]=new Integer(quan);    
		shoppingCart.add(newItem);
	    }
        }
    }
    
    public void removeItem(int id, int quan) {
        if(!shoppingCart.isEmpty()){
        
            Iterator iter = shoppingCart.iterator();
            Object arr[] = null;


            while(iter.hasNext()){
                arr = (Object[])iter.next();
                
                if(((ComputerBean)arr[0]).getID() == id) {
                    Integer antal = (Integer)arr[1];

                    if(antal.intValue() <= quan) {
                        iter.remove();
                    } else {
                        arr[1] = new Integer(antal.intValue()-quan);
                    }
                }
            }
        }
    }
    
    public String getXml() {
        StringBuffer buff = new StringBuffer();
        
        Iterator iter = shoppingCart.iterator();
        Object objBuff[] = null;
        
        while(iter.hasNext()){
            objBuff =(Object[])iter.next();
            buff.append("<tr><td>");
            buff.append(((ComputerBean)objBuff[0]).getXml2());
            buff.append("</td><td>");
            buff.append(((Integer)objBuff[1]).intValue());
            buff.append("</td></tr>");            
        }
        return buff.toString();
    }
    
}
