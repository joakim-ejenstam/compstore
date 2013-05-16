/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Martin
 */
public class OrderBean {
    
    private int orderID;
    private int userID;
    private String userName;
    private String orderContent;
    private int orderStatus;
    
    public OrderBean() {
    }  
    
    public int getID() {
        return orderID;
    }
    
    public void setID(int _orderID) {
        orderID = _orderID;
    }
    
    public int getUser() {
        return userID;
    }
    
    public void setUser(int _userID) {
        userID = _userID;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String _userName) {
        userName = _userName;
    }
    
    public String getOrderContent() {
        return orderContent;
    }
    
    public void setOrderContent(String _orderContent) {
        orderContent = _orderContent;
    }
    
    public int getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(int _orderStatus) {
        orderStatus = _orderStatus;
    }
    
    public String getXml() {
        StringBuffer xmlOut = new StringBuffer();

        xmlOut.append("Kund: ");
        xmlOut.append(userName);
        xmlOut.append("<br>Innehåll: ");
        xmlOut.append(orderContent);
        xmlOut.append("<br><br>Delar: <br>");
        if (orderStatus == 0) {
            xmlOut.append("<br><br><a href=''>Skicka</a> <br>");
        }

        return xmlOut.toString();
    }
}
