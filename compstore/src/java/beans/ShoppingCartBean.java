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
    
    public void addItem(ComputerBean cb, int quantity) {
        Object newItem[] = null;
        ComputerBean tmpBean = null;
        
        if (shoppingCart.isEmpty()) {
            newItem = new Object[2];
            newItem[0] = cb;
            newItem[1] = new Integer(quantity);    
            shoppingCart.add(newItem);
        } else {
            Iterator iter = shoppingCart.iterator();
            Object tmpArr[];
	    boolean found = false;
            
            while (iter.hasNext()) {
                tmpArr = (Object[]) iter.next();
                
                if (((ComputerBean)tmpArr[0]).getID() == cb.getID()) {
                    Integer tmpAntal = (Integer)tmpArr[1];
		    tmpArr[1]=new Integer(tmpAntal.intValue()+quantity); 
		    found= true;
                }
            }
            
            if(!found){
		newItem = new Object[2];
		newItem[0]=cb;
		newItem[1]=new Integer(quantity);    
		shoppingCart.add(newItem);
	    }
        }
    }
    
    public void removeItem() {
        
    }
    
    public String getXml() {
        StringBuffer buff = new StringBuffer();
        
        Iterator iter = shoppingCart.iterator();
        Object objBuff[] = null;
        
        while(iter.hasNext()){
            objBuff =(Object[])iter.next();
            buff.append("<tr><td>");
            buff.append(((ComputerBean)objBuff[0]).getXml());
            buff.append("</td><td>");
            buff.append(((Integer)objBuff[1]).intValue());
            buff.append("</td></tr>");            
        }
        return buff.toString();
    }
    
}
